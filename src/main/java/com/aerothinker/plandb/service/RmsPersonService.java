package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.RmsPersonDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RmsPerson.
 */
public interface RmsPersonService {

    /**
     * Save a rmsPerson.
     *
     * @param rmsPersonDTO the entity to save
     * @return the persisted entity
     */
    RmsPersonDTO save(RmsPersonDTO rmsPersonDTO);

    /**
     * Get all the rmsPeople.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RmsPersonDTO> findAll(Pageable pageable);


    /**
     * Get the "id" rmsPerson.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RmsPersonDTO> findOne(Long id);

    /**
     * Delete the "id" rmsPerson.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the rmsPerson corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RmsPersonDTO> search(String query, Pageable pageable);
}
