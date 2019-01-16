package com.aerothinker.plandb.repository;
import com.aerothinker.plandb.domain.Company;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;

import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Spring Data JPA repository for the Company entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT c FROM Company c JOIN FETCH c.users u WHERE c.id = (:id)")
    public Optional<Company> findOneAndFetchUsersEagerly(@Param("id") Long id);
}
