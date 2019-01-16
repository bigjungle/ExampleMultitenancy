package com.aerothinker.plandb.repository;

import com.aerothinker.plandb.domain.PlanInfoDataAtchHis;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanInfoDataAtchHis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanInfoDataAtchHisRepository extends JpaRepository<PlanInfoDataAtchHis, Long>, JpaSpecificationExecutor<PlanInfoDataAtchHis> {

}
