package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.PlanInfoDataHisDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanInfoDataHis.
 */
public interface PlanInfoDataHisService {

    /**
     * Save a planInfoDataHis.
     *
     * @param planInfoDataHisDTO the entity to save
     * @return the persisted entity
     */
    PlanInfoDataHisDTO save(PlanInfoDataHisDTO planInfoDataHisDTO);

    /**
     * Get all the planInfoDataHis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoDataHisDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planInfoDataHis.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanInfoDataHisDTO> findOne(Long id);

    /**
     * Delete the "id" planInfoDataHis.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the planInfoDataHis corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoDataHisDTO> search(String query, Pageable pageable);
}
