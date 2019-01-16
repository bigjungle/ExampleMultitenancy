package com.aerothinker.plandb.repository;

import com.aerothinker.plandb.domain.RmsUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the RmsUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RmsUserRepository extends JpaRepository<RmsUser, Long>, JpaSpecificationExecutor<RmsUser> {

    @Query(value = "select distinct rms_user from RmsUser rms_user left join fetch rms_user.rmsRoles",
        countQuery = "select count(distinct rms_user) from RmsUser rms_user")
    Page<RmsUser> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct rms_user from RmsUser rms_user left join fetch rms_user.rmsRoles")
    List<RmsUser> findAllWithEagerRelationships();

    @Query("select rms_user from RmsUser rms_user left join fetch rms_user.rmsRoles where rms_user.id =:id")
    Optional<RmsUser> findOneWithEagerRelationships(@Param("id") Long id);

}
