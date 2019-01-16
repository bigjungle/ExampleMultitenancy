package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.RmsUserService;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.repository.RmsUserRepository;
import com.aerothinker.plandb.repository.search.RmsUserSearchRepository;
import com.aerothinker.plandb.service.dto.RmsUserDTO;
import com.aerothinker.plandb.service.mapper.RmsUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing RmsUser.
 */
@Service
@Transactional
public class RmsUserServiceImpl implements RmsUserService {

    private final Logger log = LoggerFactory.getLogger(RmsUserServiceImpl.class);

    private final RmsUserRepository rmsUserRepository;

    private final RmsUserMapper rmsUserMapper;

    private final RmsUserSearchRepository rmsUserSearchRepository;

    public RmsUserServiceImpl(RmsUserRepository rmsUserRepository, RmsUserMapper rmsUserMapper, RmsUserSearchRepository rmsUserSearchRepository) {
        this.rmsUserRepository = rmsUserRepository;
        this.rmsUserMapper = rmsUserMapper;
        this.rmsUserSearchRepository = rmsUserSearchRepository;
    }

    /**
     * Save a rmsUser.
     *
     * @param rmsUserDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RmsUserDTO save(RmsUserDTO rmsUserDTO) {
        log.debug("Request to save RmsUser : {}", rmsUserDTO);

        RmsUser rmsUser = rmsUserMapper.toEntity(rmsUserDTO);
        rmsUser = rmsUserRepository.save(rmsUser);
        RmsUserDTO result = rmsUserMapper.toDto(rmsUser);
        rmsUserSearchRepository.save(rmsUser);
        return result;
    }

    /**
     * Get all the rmsUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RmsUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RmsUsers");
        return rmsUserRepository.findAll(pageable)
            .map(rmsUserMapper::toDto);
    }

    /**
     * Get all the RmsUser with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<RmsUserDTO> findAllWithEagerRelationships(Pageable pageable) {
        return rmsUserRepository.findAllWithEagerRelationships(pageable).map(rmsUserMapper::toDto);
    }
    

    /**
     * Get one rmsUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RmsUserDTO> findOne(Long id) {
        log.debug("Request to get RmsUser : {}", id);
        return rmsUserRepository.findOneWithEagerRelationships(id)
            .map(rmsUserMapper::toDto);
    }

    /**
     * Delete the rmsUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RmsUser : {}", id);
        rmsUserRepository.deleteById(id);
        rmsUserSearchRepository.deleteById(id);
    }

    /**
     * Search for the rmsUser corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RmsUserDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of RmsUsers for query {}", query);
        return rmsUserSearchRepository.search(queryStringQuery(query), pageable)
            .map(rmsUserMapper::toDto);
    }
}
