package com.aerothinker.plandb.repository;

import com.aerothinker.plandb.domain.PlanInfoStepAtch;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanInfoStepAtch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanInfoStepAtchRepository extends JpaRepository<PlanInfoStepAtch, Long>, JpaSpecificationExecutor<PlanInfoStepAtch> {

}
