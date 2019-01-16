package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.PlanInfoDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanInfoData.
 */
public interface PlanInfoDataService {

    /**
     * Save a planInfoData.
     *
     * @param planInfoDataDTO the entity to save
     * @return the persisted entity
     */
    PlanInfoDataDTO save(PlanInfoDataDTO planInfoDataDTO);

    /**
     * Get all the planInfoData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planInfoData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanInfoDataDTO> findOne(Long id);

    /**
     * Delete the "id" planInfoData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the planInfoData corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoDataDTO> search(String query, Pageable pageable);
}
