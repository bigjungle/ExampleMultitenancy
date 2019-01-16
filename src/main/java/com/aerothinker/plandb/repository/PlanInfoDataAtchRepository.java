package com.aerothinker.plandb.repository;

import com.aerothinker.plandb.domain.PlanInfoDataAtch;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanInfoDataAtch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanInfoDataAtchRepository extends JpaRepository<PlanInfoDataAtch, Long>, JpaSpecificationExecutor<PlanInfoDataAtch> {

}
