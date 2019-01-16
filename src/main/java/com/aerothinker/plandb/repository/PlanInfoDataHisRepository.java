package com.aerothinker.plandb.repository;

import com.aerothinker.plandb.domain.PlanInfoDataHis;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanInfoDataHis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanInfoDataHisRepository extends JpaRepository<PlanInfoDataHis, Long>, JpaSpecificationExecutor<PlanInfoDataHis> {

}
