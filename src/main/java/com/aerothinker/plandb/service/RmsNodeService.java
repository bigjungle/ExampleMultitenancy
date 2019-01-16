package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.RmsNodeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RmsNode.
 */
public interface RmsNodeService {

    /**
     * Save a rmsNode.
     *
     * @param rmsNodeDTO the entity to save
     * @return the persisted entity
     */
    RmsNodeDTO save(RmsNodeDTO rmsNodeDTO);

    /**
     * Get all the rmsNodes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RmsNodeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" rmsNode.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RmsNodeDTO> findOne(Long id);

    /**
     * Delete the "id" rmsNode.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the rmsNode corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RmsNodeDTO> search(String query, Pageable pageable);
}
