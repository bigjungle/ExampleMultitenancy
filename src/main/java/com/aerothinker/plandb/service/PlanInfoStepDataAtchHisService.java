package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchHisDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanInfoStepDataAtchHis.
 */
public interface PlanInfoStepDataAtchHisService {

    /**
     * Save a planInfoStepDataAtchHis.
     *
     * @param planInfoStepDataAtchHisDTO the entity to save
     * @return the persisted entity
     */
    PlanInfoStepDataAtchHisDTO save(PlanInfoStepDataAtchHisDTO planInfoStepDataAtchHisDTO);

    /**
     * Get all the planInfoStepDataAtchHis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoStepDataAtchHisDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planInfoStepDataAtchHis.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanInfoStepDataAtchHisDTO> findOne(Long id);

    /**
     * Delete the "id" planInfoStepDataAtchHis.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the planInfoStepDataAtchHis corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoStepDataAtchHisDTO> search(String query, Pageable pageable);
}
