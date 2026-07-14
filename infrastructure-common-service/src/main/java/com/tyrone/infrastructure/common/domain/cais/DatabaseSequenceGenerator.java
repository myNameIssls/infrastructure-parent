package com.tyrone.infrastructure.common.domain.cais;

import com.tyrone.infrastructure.common.acl.port.repository.CAISRepository;
import com.tyrone.infrastructure.sdk.common.domain.cais.SequenceType;
import com.tyrone.infrastructure.sdk.common.pl.GenCAISIdRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseSequenceGenerator implements SequenceGenerator {

    private final CAISRepository caisRepository;

    @Override
    @Transactional
    public String generate(GenCAISIdRequest cmd) {
        CAISId caisId = new CAISId(cmd.getPrefix(), cmd.getSegmentRule().getSegmentRuleValue());
        CustomAutoIncreaseSequence cais = caisRepository.findByIdLock(caisId);

        if (Objects.isNull(cais)) {
            cais = new CustomAutoIncreaseSequence(cmd);
            try {
                caisRepository.save(cais);
                return cais.format();
            } catch (DuplicateKeyException e) {
                log.warn("Sequence record already exists for id: {}, retrying...", caisId);
                cais = caisRepository.findByIdLock(caisId);
            }
        }

        cais.increase();
        caisRepository.save(cais);

        return cais.format();
    }

    @Override
    public SequenceType getType() {
        return SequenceType.DATABASE;
    }

}