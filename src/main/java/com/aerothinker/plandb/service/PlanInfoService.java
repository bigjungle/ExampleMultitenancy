package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.PlanInfoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanInfo.
 */
public interface PlanInfoService {

    /**
     * Save a planInfo.
     *
     * @param planInfoDTO the entity to save
     * @return the persisted entity
     */
    PlanInfoDTO save(PlanInfoDTO planInfoDTO);

    /**
     * Get all the planInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanInfoDTO> findOne(Long id);

    /**
     * Delete the "id" planInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the planInfo corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanInfoDTO> search(String query, Pageable pageable);
}
