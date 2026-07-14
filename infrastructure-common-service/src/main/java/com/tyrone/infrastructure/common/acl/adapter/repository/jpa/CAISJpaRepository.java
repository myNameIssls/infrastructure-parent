package com.tyrone.infrastructure.common.acl.adapter.repository.jpa;

import com.tyrone.infrastructure.common.domain.cais.CAISId;
import com.tyrone.infrastructure.common.domain.cais.CustomAutoIncreaseSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface CAISJpaRepository extends JpaRepository<CustomAutoIncreaseSequence, CAISId>, JpaSpecificationExecutor<CustomAutoIncreaseSequence> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CustomAutoIncreaseSequence c WHERE c.caisId = :caisId")
    Optional<CustomAutoIncreaseSequence> findByIdForUpdate(@Param("caisId") CAISId caisId);

}