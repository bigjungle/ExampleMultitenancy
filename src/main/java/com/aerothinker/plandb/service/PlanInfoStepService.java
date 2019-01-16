package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.PlanInfoStepDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanInfoStep.
 */
public interface PlanInfoStepService {

    /**
     * Save a planInfoStep.
     *
     * @param planInfoStepDTO the entity to save
     * @return the persisted entity
     */
    PlanInfoStepDTO save(PlanInfoStepDTO planInfoStepDTO);

    /**
     * Get all the planInfoSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoStepDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planInfoStep.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanInfoStepDTO> findOne(Long id);

    /**
     * Delete the "id" planInfoStep.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the planInfoStep corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoStepDTO> search(String query, Pageable pageable);
}
