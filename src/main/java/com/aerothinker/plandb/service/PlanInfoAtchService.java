package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.PlanInfoAtchDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanInfoAtch.
 */
public interface PlanInfoAtchService {

    /**
     * Save a planInfoAtch.
     *
     * @param planInfoAtchDTO the entity to save
     * @return the persisted entity
     */
    PlanInfoAtchDTO save(PlanInfoAtchDTO planInfoAtchDTO);

    /**
     * Get all the planInfoAtches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoAtchDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planInfoAtch.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanInfoAtchDTO> findOne(Long id);

    /**
     * Delete the "id" planInfoAtch.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the planInfoAtch corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoAtchDTO> search(String query, Pageable pageable);
}
