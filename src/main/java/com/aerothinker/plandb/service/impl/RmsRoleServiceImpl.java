package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.RmsRoleService;
import com.aerothinker.plandb.domain.RmsRole;
import com.aerothinker.plandb.repository.RmsRoleRepository;
import com.aerothinker.plandb.repository.search.RmsRoleSearchRepository;
import com.aerothinker.plandb.service.dto.RmsRoleDTO;
import com.aerothinker.plandb.service.mapper.RmsRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing RmsRole.
 */
@Service
@Transactional
public class RmsRoleServiceImpl implements RmsRoleService {

    private final Logger log = LoggerFactory.getLogger(RmsRoleServiceImpl.class);

    private final RmsRoleRepository rmsRoleRepository;

    private final RmsRoleMapper rmsRoleMapper;

    private final RmsRoleSearchRepository rmsRoleSearchRepository;

    public RmsRoleServiceImpl(RmsRoleRepository rmsRoleRepository, RmsRoleMapper rmsRoleMapper, RmsRoleSearchRepository rmsRoleSearchRepository) {
        this.rmsRoleRepository = rmsRoleRepository;
        this.rmsRoleMapper = rmsRoleMapper;
        this.rmsRoleSearchRepository = rmsRoleSearchRepository;
    }

    /**
     * Save a rmsRole.
     *
     * @param rmsRoleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RmsRoleDTO save(RmsRoleDTO rmsRoleDTO) {
        log.debug("Request to save RmsRole : {}", rmsRoleDTO);

        RmsRole rmsRole = rmsRoleMapper.toEntity(rmsRoleDTO);
        rmsRole = rmsRoleRepository.save(rmsRole);
        RmsRoleDTO result = rmsRoleMapper.toDto(rmsRole);
        rmsRoleSearchRepository.save(rmsRole);
        return result;
    }

    /**
     * Get all the rmsRoles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RmsRoleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RmsRoles");
        return rmsRoleRepository.findAll(pageable)
            .map(rmsRoleMapper::toDto);
    }

    /**
     * Get all the RmsRole with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<RmsRoleDTO> findAllWithEagerRelationships(Pageable pageable) {
        return rmsRoleRepository.findAllWithEagerRelationships(pageable).map(rmsRoleMapper::toDto);
    }
    

    /**
     * Get one rmsRole by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RmsRoleDTO> findOne(Long id) {
        log.debug("Request to get RmsRole : {}", id);
        return rmsRoleRepository.findOneWithEagerRelationships(id)
            .map(rmsRoleMapper::toDto);
    }

    /**
     * Delete the rmsRole by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RmsRole : {}", id);
        rmsRoleRepository.deleteById(id);
        rmsRoleSearchRepository.deleteById(id);
    }

    /**
     * Search for the rmsRole corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RmsRoleDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of RmsRoles for query {}", query);
        return rmsRoleSearchRepository.search(queryStringQuery(query), pageable)
            .map(rmsRoleMapper::toDto);
    }
}
