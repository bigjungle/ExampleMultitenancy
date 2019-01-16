package com.aerothinker.plandb.repository;

import com.aerothinker.plandb.domain.PlanInfoStepDataHis;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanInfoStepDataHis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanInfoStepDataHisRepository extends JpaRepository<PlanInfoStepDataHis, Long>, JpaSpecificationExecutor<PlanInfoStepDataHis> {

}
