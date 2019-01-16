package com.aerothinker.plandb.repository;

import com.aerothinker.plandb.domain.PlanInfoData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanInfoData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanInfoDataRepository extends JpaRepository<PlanInfoData, Long>, JpaSpecificationExecutor<PlanInfoData> {

}
