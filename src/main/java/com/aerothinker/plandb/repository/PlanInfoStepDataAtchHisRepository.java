package com.aerothinker.plandb.repository;

import com.aerothinker.plandb.domain.PlanInfoStepDataAtchHis;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanInfoStepDataAtchHis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanInfoStepDataAtchHisRepository extends JpaRepository<PlanInfoStepDataAtchHis, Long>, JpaSpecificationExecutor<PlanInfoStepDataAtchHis> {

}
