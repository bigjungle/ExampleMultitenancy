package com.aerothinker.plandb.repository;

import com.aerothinker.plandb.domain.RmsPerson;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RmsPerson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RmsPersonRepository extends JpaRepository<RmsPerson, Long>, JpaSpecificationExecutor<RmsPerson> {

}
