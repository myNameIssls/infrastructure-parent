package com.tyrone.infrastructure.common.domain.cais;

import com.tyrone.infrastructure.sdk.common.domain.cais.SegmentRule;
import com.tyrone.infrastructure.sdk.common.domain.cais.SegmentRuleFactory;
import com.tyrone.infrastructure.sdk.common.pl.GenCAISIdRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CustomAutoIncreaseSequence 单元测试
 */
class CustomAutoIncreaseSequenceTest {

    @Test
    @DisplayName("测试格式化方法 - 使用日期分段规则")
    void testFormatWithLocalDateTimeSegmentRule() {
        SegmentRule segmentRule = SegmentRuleFactory.getLocalDateTimeInstance("yyyyMMdd");
        GenCAISIdRequest command = GenCAISIdRequest.builder()
                .segmentRule(segmentRule)
                .prefix("CZ")
                .joiner("-")
                .length(4)
                .build();

        CustomAutoIncreaseSequence cais = new CustomAutoIncreaseSequence(command);
        String format = cais.format();

        // 验证格式: CZ-yyyyMMdd-0001
        assertTrue(format.startsWith("CZ-"));
        assertTrue(format.endsWith("-0001"));
        assertTrue(Pattern.matches("CZ-\\d{8}-0001", format));

        // 验证序列递增
        cais.increase();
        String format2 = cais.format();
        assertTrue(Pattern.matches("CZ-\\d{8}-0002", format2));
    }

    @Test
    @DisplayName("测试格式化方法 - 无分段规则")
    void testFormatWithNoneSegmentRule() {
        SegmentRule segmentRule = SegmentRuleFactory.getNoneInstance();
        GenCAISIdRequest command = GenCAISIdRequest.builder()
                .segmentRule(segmentRule)
                .prefix("ORDER")
                .joiner("")
                .length(6)
                .build();

        CustomAutoIncreaseSequence cais = new CustomAutoIncreaseSequence(command);
        String format = cais.format();

        // 验证格式: ORDER000001 (无连接符)
        assertTrue(format.startsWith("ORDER"));
        assertTrue(Pattern.matches("ORDER\\d{6}", format));
        assertEquals(11, format.length()); // ORDER(5) + sequence(6)
    }

    @Test
    @DisplayName("测试序列递增")
    void testIncrease() {
        SegmentRule segmentRule = SegmentRuleFactory.getNoneInstance();
        GenCAISIdRequest command = GenCAISIdRequest.builder()
                .segmentRule(segmentRule)
                .prefix("TEST")
                .joiner("-")
                .length(3)
                .build();

        CustomAutoIncreaseSequence cais = new CustomAutoIncreaseSequence(command);

        // 初始值为1
        String format1 = cais.format();
        assertTrue(format1.endsWith("-001"));

        // 递增后为2
        cais.increase();
        String format2 = cais.format();
        assertTrue(format2.endsWith("-002"));

        // 再递增后为3
        cais.increase();
        String format3 = cais.format();
        assertTrue(format3.endsWith("-003"));
    }

    @Test
    @DisplayName("测试序列长度补零")
    void testSequencePadding() {
        SegmentRule segmentRule = SegmentRuleFactory.getNoneInstance();
        GenCAISIdRequest command = GenCAISIdRequest.builder()
                .segmentRule(segmentRule)
                .prefix("ID")
                .joiner("")
                .length(10)
                .build();

        CustomAutoIncreaseSequence cais = new CustomAutoIncreaseSequence(command);
        String format = cais.format();

        // 验证10位补零
        assertTrue(format.startsWith("ID"));
        assertTrue(format.endsWith("0000000001"));
        assertEquals(12, format.length()); // ID(2) + sequence(10)
    }

    @Test
    @DisplayName("测试自定义日期格式")
    void testCustomDateFormat() {
        SegmentRule segmentRule = SegmentRuleFactory.getLocalDateTimeInstance("yyMM");
        GenCAISIdRequest command = GenCAISIdRequest.builder()
                .segmentRule(segmentRule)
                .prefix("INV")
                .joiner("/")
                .length(4)
                .build();

        CustomAutoIncreaseSequence cais = new CustomAutoIncreaseSequence(command);
        String format = cais.format();

        // 验证格式: INV/yyMM/0001
        assertTrue(format.startsWith("INV/"));
        assertTrue(format.endsWith("/0001"));
        assertTrue(Pattern.matches("INV/\\d{4}/0001", format));
    }

    @Test
    @DisplayName("测试CAISId创建")
    void testCAISIdCreation() {
        CAISId caisId = new CAISId("PREFIX", "RULE");

        assertEquals("PREFIX", caisId.getPrefix());
        assertEquals("RULE", caisId.getSegmentRule());
    }

}