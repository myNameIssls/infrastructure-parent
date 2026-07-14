package com.tyrone.infrastructure.common.acl.adapter.repository;

import com.tyrone.infrastructure.common.acl.port.repository.CAISRepository;
import com.tyrone.infrastructure.common.acl.adapter.repository.jpa.CAISJpaRepository;
import com.tyrone.infrastructure.common.domain.cais.CAISId;
import com.tyrone.infrastructure.common.domain.cais.CustomAutoIncreaseSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CAISRepositoryJpaAdapter implements CAISRepository {

    private final CAISJpaRepository jpaRepository;

    @Override
    public CustomAutoIncreaseSequence findByIdLock(CAISId caisId) {
        return jpaRepository.findByIdForUpdate(caisId).orElse(null);
    }

    @Override
    public void save(CustomAutoIncreaseSequence cais) {
        jpaRepository.save(cais);
    }
}