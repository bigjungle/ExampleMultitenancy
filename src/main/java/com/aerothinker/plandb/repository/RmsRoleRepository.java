package com.aerothinker.plandb.repository;

import com.aerothinker.plandb.domain.RmsRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the RmsRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RmsRoleRepository extends JpaRepository<RmsRole, Long>, JpaSpecificationExecutor<RmsRole> {

    @Query(value = "select distinct rms_role from RmsRole rms_role left join fetch rms_role.rmsNodes",
        countQuery = "select count(distinct rms_role) from RmsRole rms_role")
    Page<RmsRole> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct rms_role from RmsRole rms_role left join fetch rms_role.rmsNodes")
    List<RmsRole> findAllWithEagerRelationships();

    @Query("select rms_role from RmsRole rms_role left join fetch rms_role.rmsNodes where rms_role.id =:id")
    Optional<RmsRole> findOneWithEagerRelationships(@Param("id") Long id);

}
