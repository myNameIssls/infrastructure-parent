package com.tyrone.infrastructure.common.cais.acl.adapter.repository.jpa;

import com.tyrone.infrastructure.common.cais.domain.CAISId;
import com.tyrone.infrastructure.common.cais.domain.CustomAutoIncreaseSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CAISJpaRepostory extends JpaRepository<CustomAutoIncreaseSequence, CAISId>, JpaSpecificationExecutor<CustomAutoIncreaseSequence> {



}
