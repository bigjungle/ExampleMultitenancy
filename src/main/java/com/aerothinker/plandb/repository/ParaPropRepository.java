package com.aerothinker.plandb.repository;

import com.aerothinker.plandb.domain.ParaProp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ParaProp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParaPropRepository extends JpaRepository<ParaProp, Long>, JpaSpecificationExecutor<ParaProp> {

}
