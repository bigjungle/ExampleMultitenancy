package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.RmsNodeService;
import com.aerothinker.plandb.domain.RmsNode;
import com.aerothinker.plandb.repository.RmsNodeRepository;
import com.aerothinker.plandb.repository.search.RmsNodeSearchRepository;
import com.aerothinker.plandb.service.dto.RmsNodeDTO;
import com.aerothinker.plandb.service.mapper.RmsNodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing RmsNode.
 */
@Service
@Transactional
public class RmsNodeServiceImpl implements RmsNodeService {

    private final Logger log = LoggerFactory.getLogger(RmsNodeServiceImpl.class);

    private final RmsNodeRepository rmsNodeRepository;

    private final RmsNodeMapper rmsNodeMapper;

    private final RmsNodeSearchRepository rmsNodeSearchRepository;

    public RmsNodeServiceImpl(RmsNodeRepository rmsNodeRepository, RmsNodeMapper rmsNodeMapper, RmsNodeSearchRepository rmsNodeSearchRepository) {
        this.rmsNodeRepository = rmsNodeRepository;
        this.rmsNodeMapper = rmsNodeMapper;
        this.rmsNodeSearchRepository = rmsNodeSearchRepository;
    }

    /**
     * Save a rmsNode.
     *
     * @param rmsNodeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RmsNodeDTO save(RmsNodeDTO rmsNodeDTO) {
        log.debug("Request to save RmsNode : {}", rmsNodeDTO);

        RmsNode rmsNode = rmsNodeMapper.toEntity(rmsNodeDTO);
        rmsNode = rmsNodeRepository.save(rmsNode);
        RmsNodeDTO result = rmsNodeMapper.toDto(rmsNode);
        rmsNodeSearchRepository.save(rmsNode);
        return result;
    }

    /**
     * Get all the rmsNodes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RmsNodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RmsNodes");
        return rmsNodeRepository.findAll(pageable)
            .map(rmsNodeMapper::toDto);
    }


    /**
     * Get one rmsNode by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RmsNodeDTO> findOne(Long id) {
        log.debug("Request to get RmsNode : {}", id);
        return rmsNodeRepository.findById(id)
            .map(rmsNodeMapper::toDto);
    }

    /**
     * Delete the rmsNode by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RmsNode : {}", id);
        rmsNodeRepository.deleteById(id);
        rmsNodeSearchRepository.deleteById(id);
    }

    /**
     * Search for the rmsNode corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RmsNodeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of RmsNodes for query {}", query);
        return rmsNodeSearchRepository.search(queryStringQuery(query), pageable)
            .map(rmsNodeMapper::toDto);
    }
}
