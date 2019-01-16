package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.PlanInfoStepDataHisDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanInfoStepDataHis.
 */
public interface PlanInfoStepDataHisService {

    /**
     * Save a planInfoStepDataHis.
     *
     * @param planInfoStepDataHisDTO the entity to save
     * @return the persisted entity
     */
    PlanInfoStepDataHisDTO save(PlanInfoStepDataHisDTO planInfoStepDataHisDTO);

    /**
     * Get all the planInfoStepDataHis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoStepDataHisDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planInfoStepDataHis.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanInfoStepDataHisDTO> findOne(Long id);

    /**
     * Delete the "id" planInfoStepDataHis.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the planInfoStepDataHis corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoStepDataHisDTO> search(String query, Pageable pageable);
}
