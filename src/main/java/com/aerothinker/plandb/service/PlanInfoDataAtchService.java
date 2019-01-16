package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.PlanInfoDataAtchDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanInfoDataAtch.
 */
public interface PlanInfoDataAtchService {

    /**
     * Save a planInfoDataAtch.
     *
     * @param planInfoDataAtchDTO the entity to save
     * @return the persisted entity
     */
    PlanInfoDataAtchDTO save(PlanInfoDataAtchDTO planInfoDataAtchDTO);

    /**
     * Get all the planInfoDataAtches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoDataAtchDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planInfoDataAtch.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanInfoDataAtchDTO> findOne(Long id);

    /**
     * Delete the "id" planInfoDataAtch.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the planInfoDataAtch corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoDataAtchDTO> search(String query, Pageable pageable);
}
