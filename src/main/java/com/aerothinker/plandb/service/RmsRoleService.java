package com.aerothinker.plandb.service;

import com.aerothinker.plandb.service.dto.RmsRoleDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RmsRole.
 */
public interface RmsRoleService {

    /**
     * Save a rmsRole.
     *
     * @param rmsRoleDTO the entity to save
     * @return the persisted entity
     */
    RmsRoleDTO save(RmsRoleDTO rmsRoleDTO);

    /**
     * Get all the rmsRoles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RmsRoleDTO> findAll(Pageable pageable);

    /**
     * Get all the RmsRole with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<RmsRoleDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" rmsRole.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RmsRoleDTO> findOne(Long id);

    /**
     * Delete the "id" rmsRole.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the rmsRole corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RmsRoleDTO> search(String query, Pageable pageable);
}
