package com.tyrone.infrastructure.common.acl.adapter.repository;

import com.tyrone.infrastructure.common.acl.port.repository.CAISRepository;
import com.tyrone.infrastructure.common.acl.adapter.repository.jpa.CAISJpaRepostory;
import com.tyrone.infrastructure.common.domain.cais.CAISId;
import com.tyrone.infrastructure.common.domain.cais.CustomAutoIncreaseSequence;
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
