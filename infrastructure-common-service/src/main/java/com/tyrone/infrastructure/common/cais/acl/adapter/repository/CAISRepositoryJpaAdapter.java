package com.tyrone.infrastructure.common.cais.acl.adapter.repository;

import com.tyrone.infrastructure.common.cais.acl.adapter.repository.jpa.CAISJpaRepostory;
import com.tyrone.infrastructure.common.cais.acl.port.repository.CAISRepository;
import com.tyrone.infrastructure.common.cais.domain.CAISId;
import com.tyrone.infrastructure.common.cais.domain.CustomAutoIncreaseSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CAISRepositoryJpaAdapter implements CAISRepository {

    private final CAISJpaRepostory jpaRepostory;

    @Override
    public CustomAutoIncreaseSequence findByIdLock(CAISId caisId) {

        return jpaRepostory.findById(caisId).get();

    }

    @Override
    public void save(CustomAutoIncreaseSequence cais) {
        jpaRepostory.save(cais);
    }
}
