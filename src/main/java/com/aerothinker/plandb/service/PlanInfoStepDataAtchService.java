package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanInfoStepDataAtch.
 */
public interface PlanInfoStepDataAtchService {

    /**
     * Save a planInfoStepDataAtch.
     *
     * @param planInfoStepDataAtchDTO the entity to save
     * @return the persisted entity
     */
    PlanInfoStepDataAtchDTO save(PlanInfoStepDataAtchDTO planInfoStepDataAtchDTO);

    /**
     * Get all the planInfoStepDataAtches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoStepDataAtchDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planInfoStepDataAtch.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanInfoStepDataAtchDTO> findOne(Long id);

    /**
     * Delete the "id" planInfoStepDataAtch.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the planInfoStepDataAtch corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoStepDataAtchDTO> search(String query, Pageable pageable);
}
