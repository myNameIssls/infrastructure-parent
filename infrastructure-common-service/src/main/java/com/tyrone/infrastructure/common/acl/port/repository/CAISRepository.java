package com.tyrone.infrastructure.common.acl.port.repository;

import com.tyrone.infrastructure.common.domain.cais.CAISId;
import com.tyrone.infrastructure.common.domain.cais.CustomAutoIncreaseSequence;

public interface CAISRepository {

    CustomAutoIncreaseSequence findByIdLock(CAISId caisId);

    void save(CustomAutoIncreaseSequence cais);
}
