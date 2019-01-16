package com.aerothinker.plandb.repository;

import com.aerothinker.plandb.domain.PlanInfoStep;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanInfoStep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanInfoStepRepository extends JpaRepository<PlanInfoStep, Long>, JpaSpecificationExecutor<PlanInfoStep> {

}
