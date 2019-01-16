package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.PlanInfoStepDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanInfoStepData.
 */
public interface PlanInfoStepDataService {

    /**
     * Save a planInfoStepData.
     *
     * @param planInfoStepDataDTO the entity to save
     * @return the persisted entity
     */
    PlanInfoStepDataDTO save(PlanInfoStepDataDTO planInfoStepDataDTO);

    /**
     * Get all the planInfoStepData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoStepDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planInfoStepData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanInfoStepDataDTO> findOne(Long id);

    /**
     * Delete the "id" planInfoStepData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the planInfoStepData corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoStepDataDTO> search(String query, Pageable pageable);
}
