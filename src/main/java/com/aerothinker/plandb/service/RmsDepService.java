package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.RmsDepDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RmsDep.
 */
public interface RmsDepService {

    /**
     * Save a rmsDep.
     *
     * @param rmsDepDTO the entity to save
     * @return the persisted entity
     */
    RmsDepDTO save(RmsDepDTO rmsDepDTO);

    /**
     * Get all the rmsDeps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RmsDepDTO> findAll(Pageable pageable);


    /**
     * Get the "id" rmsDep.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RmsDepDTO> findOne(Long id);

    /**
     * Delete the "id" rmsDep.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the rmsDep corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RmsDepDTO> search(String query, Pageable pageable);
}
