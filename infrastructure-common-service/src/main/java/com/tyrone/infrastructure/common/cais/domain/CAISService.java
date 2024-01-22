package com.tyrone.infrastructure.common.cais.domain;

import com.tyrone.infrastructure.common.cais.acl.port.repository.CAISRepository;
import com.tyrone.infrastructure.core.pl.Response;
import com.tyrone.infrastructure.sdk.common.cais.pl.GenCAISIdCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CAISService {

    private final CAISRepository caisRepository;

    @Transactional
    public Response<String> generate(GenCAISIdCommand cmd) {

        CAISId caisId = new CAISId(cmd.getPrefix(), cmd.getSegmentRule().getSegmentRuleValue());
        CustomAutoIncreaseSequence cais = caisRepository.findByIdLock(caisId);

        if (Objects.isNull(cais)) {

            cais = new CustomAutoIncreaseSequence(cmd);

            try {
                caisRepository.save(cais);
                return Response.success(cais.format());
            } catch (DuplicateKeyException e) {
                cais = caisRepository.findByIdLock(caisId);
            }

        }

        cais.increase();
        caisRepository.save(cais);

        return Response.success(cais.format());

    }

}
