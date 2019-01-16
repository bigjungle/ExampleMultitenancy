package com.aerothinker.plandb.repository;

import com.aerothinker.plandb.domain.ParaClass;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ParaClass entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParaClassRepository extends JpaRepository<ParaClass, Long>, JpaSpecificationExecutor<ParaClass> {

}
