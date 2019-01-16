package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.PlanInfoDataAtchHisDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanInfoDataAtchHis.
 */
public interface PlanInfoDataAtchHisService {

    /**
     * Save a planInfoDataAtchHis.
     *
     * @param planInfoDataAtchHisDTO the entity to save
     * @return the persisted entity
     */
    PlanInfoDataAtchHisDTO save(PlanInfoDataAtchHisDTO planInfoDataAtchHisDTO);

    /**
     * Get all the planInfoDataAtchHis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoDataAtchHisDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planInfoDataAtchHis.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanInfoDataAtchHisDTO> findOne(Long id);

    /**
     * Delete the "id" planInfoDataAtchHis.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the planInfoDataAtchHis corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoDataAtchHisDTO> search(String query, Pageable pageable);
}
