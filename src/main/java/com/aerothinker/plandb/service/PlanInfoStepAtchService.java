package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.PlanInfoStepAtchDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanInfoStepAtch.
 */
public interface PlanInfoStepAtchService {

    /**
     * Save a planInfoStepAtch.
     *
     * @param planInfoStepAtchDTO the entity to save
     * @return the persisted entity
     */
    PlanInfoStepAtchDTO save(PlanInfoStepAtchDTO planInfoStepAtchDTO);

    /**
     * Get all the planInfoStepAtches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoStepAtchDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planInfoStepAtch.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanInfoStepAtchDTO> findOne(Long id);

    /**
     * Delete the "id" planInfoStepAtch.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the planInfoStepAtch corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoStepAtchDTO> search(String query, Pageable pageable);
}
