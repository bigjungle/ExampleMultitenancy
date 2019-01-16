package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.RmsUserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RmsUser.
 */
public interface RmsUserService {

    /**
     * Save a rmsUser.
     *
     * @param rmsUserDTO the entity to save
     * @return the persisted entity
     */
    RmsUserDTO save(RmsUserDTO rmsUserDTO);

    /**
     * Get all the rmsUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RmsUserDTO> findAll(Pageable pageable);

    /**
     * Get all the RmsUser with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<RmsUserDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" rmsUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RmsUserDTO> findOne(Long id);

    /**
     * Delete the "id" rmsUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the rmsUser corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RmsUserDTO> search(String query, Pageable pageable);
}
