package com.tyrone.infrastructure.common.cais.acl.port.repository;

import com.tyrone.infrastructure.common.cais.domain.CAISId;
import com.tyrone.infrastructure.common.cais.domain.CustomAutoIncreaseSequence;

public interface CAISRepository {

    CustomAutoIncreaseSequence findByIdLock(CAISId caisId);

    void save(CustomAutoIncreaseSequence cais);
}
