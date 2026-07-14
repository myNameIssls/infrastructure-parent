package com.tyrone.infrastructure.common.domain.cais;

import com.tyrone.infrastructure.common.acl.port.repository.CAISRepository;
import com.tyrone.infrastructure.sdk.common.domain.cais.SegmentRule;
import com.tyrone.infrastructure.sdk.common.domain.cais.SegmentRuleFactory;
import com.tyrone.infrastructure.sdk.common.domain.cais.SequenceType;
import com.tyrone.infrastructure.sdk.common.pl.GenCAISIdRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * CAISService 单元测试
 */
@ExtendWith(MockitoExtension.class)
class CAISServiceTest {

    @Mock
    private CAISRepository caisRepository;

    private CAISService caisService;

    private DatabaseSequenceGenerator databaseGenerator;
    private UuidSequenceGenerator uuidGenerator;
    private SnowflakeSequenceGenerator snowflakeGenerator;

    @BeforeEach
    void setUp() {
        databaseGenerator = new DatabaseSequenceGenerator(caisRepository);
        uuidGenerator = new UuidSequenceGenerator();
        snowflakeGenerator = new SnowflakeSequenceGenerator();

        caisService = new CAISService(List.of(databaseGenerator, uuidGenerator, snowflakeGenerator));
    }

    private GenCAISIdRequest buildRequest(SequenceType type) {
        SegmentRule segmentRule = SegmentRuleFactory.getLocalDateTimeInstance("yyyyMMdd");
        return GenCAISIdRequest.builder()
                .segmentRule(segmentRule)
                .prefix("CZ")
                .joiner("-")
                .length(4)
                .sequenceType(type)
                .build();
    }

    @Test
    @DisplayName("测试生成ID - DATABASE方式新序列")
    void testGenerateDatabaseNewSequence() {
        GenCAISIdRequest request = buildRequest(SequenceType.DATABASE);

        when(caisRepository.findByIdLock(any(CAISId.class))).thenReturn(null);
        doNothing().when(caisRepository).save(any(CustomAutoIncreaseSequence.class));

        String id = caisService.generate(request);

        assertNotNull(id);
        assertTrue(Pattern.matches("CZ-\\d{8}-0001", id));

        verify(caisRepository).findByIdLock(any(CAISId.class));
        verify(caisRepository).save(any(CustomAutoIncreaseSequence.class));
    }

    @Test
    @DisplayName("测试生成ID - DATABASE方式已有序列")
    void testGenerateDatabaseExistingSequence() {
        SegmentRule segmentRule = SegmentRuleFactory.getNoneInstance();
        GenCAISIdRequest request = GenCAISIdRequest.builder()
                .segmentRule(segmentRule)
                .prefix("CZ")
                .joiner("-")
                .length(4)
                .sequenceType(SequenceType.DATABASE)
                .build();

        CustomAutoIncreaseSequence existingSequence = new CustomAutoIncreaseSequence(request);

        when(caisRepository.findByIdLock(any(CAISId.class))).thenReturn(existingSequence);
        doNothing().when(caisRepository).save(any(CustomAutoIncreaseSequence.class));

        String id = caisService.generate(request);

        assertNotNull(id);
        assertTrue(Pattern.matches("CZ--0002", id));
    }

    @Test
    @DisplayName("测试生成ID - DATABASE方式并发冲突")
    void testGenerateDatabaseDuplicateKey() {
        GenCAISIdRequest request = buildRequest(SequenceType.DATABASE);

        when(caisRepository.findByIdLock(any(CAISId.class)))
                .thenReturn(null)
                .thenReturn(new CustomAutoIncreaseSequence(request));

        doThrow(new DuplicateKeyException("Duplicate key"))
                .doNothing()
                .when(caisRepository).save(any(CustomAutoIncreaseSequence.class));

        String id = caisService.generate(request);

        assertNotNull(id);
        assertTrue(Pattern.matches("CZ-\\d{8}-0002", id));

        verify(caisRepository, times(2)).findByIdLock(any(CAISId.class));
        verify(caisRepository, times(2)).save(any(CustomAutoIncreaseSequence.class));
    }

    @Test
    @DisplayName("测试生成ID - UUID方式")
    void testGenerateUuid() {
        GenCAISIdRequest request = buildRequest(SequenceType.UUID);

        String id = caisService.generate(request);

        assertNotNull(id);
        assertTrue(id.startsWith("CZ-"));
        assertTrue(Pattern.matches("CZ-\\d{8}-[a-f0-9]{32}", id));
    }

    @Test
    @DisplayName("测试生成ID - SNOWFLAKE方式")
    void testGenerateSnowflake() {
        GenCAISIdRequest request = buildRequest(SequenceType.SNOWFLAKE);

        String id = caisService.generate(request);

        assertNotNull(id);
        assertTrue(id.startsWith("CZ-"));
        assertTrue(Pattern.matches("CZ-\\d{8}-\\d+", id));
    }

    @Test
    @DisplayName("测试生成ID - 默认使用DATABASE方式")
    void testGenerateDefaultType() {
        SegmentRule segmentRule = SegmentRuleFactory.getNoneInstance();
        GenCAISIdRequest request = GenCAISIdRequest.builder()
                .segmentRule(segmentRule)
                .prefix("TEST")
                .joiner("")
                .length(6)
                .build();

        when(caisRepository.findByIdLock(any(CAISId.class))).thenReturn(null);
        doNothing().when(caisRepository).save(any(CustomAutoIncreaseSequence.class));

        String id = caisService.generate(request);

        assertNotNull(id);
        assertTrue(Pattern.matches("TEST\\d{6}", id));
    }

    @Test
    @DisplayName("测试生成ID - UUID方式无前缀无分段")
    void testGenerateUuidNoPrefixNoSegment() {
        SegmentRule segmentRule = SegmentRuleFactory.getNoneInstance();
        GenCAISIdRequest request = GenCAISIdRequest.builder()
                .segmentRule(segmentRule)
                .prefix("")
                .joiner("")
                .length(6)
                .sequenceType(SequenceType.UUID)
                .build();

        String id = caisService.generate(request);

        assertNotNull(id);
        assertEquals(32, id.length());
    }

    @Test
    @DisplayName("测试生成ID - 不支持的类型抛出异常")
    void testGenerateUnsupportedType() {
        GenCAISIdRequest request = GenCAISIdRequest.builder()
                .segmentRule(SegmentRuleFactory.getNoneInstance())
                .prefix("TEST")
                .sequenceType(null)
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            CAISService emptyService = new CAISService(List.of());
            request.setSequenceType(SequenceType.DATABASE);
            emptyService.generate(request);
        });
    }

}