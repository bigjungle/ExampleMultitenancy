package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.RmsDepService;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.repository.RmsDepRepository;
import com.aerothinker.plandb.repository.search.RmsDepSearchRepository;
import com.aerothinker.plandb.service.dto.RmsDepDTO;
import com.aerothinker.plandb.service.mapper.RmsDepMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing RmsDep.
 */
@Service
@Transactional
public class RmsDepServiceImpl implements RmsDepService {

    private final Logger log = LoggerFactory.getLogger(RmsDepServiceImpl.class);

    private final RmsDepRepository rmsDepRepository;

    private final RmsDepMapper rmsDepMapper;

    private final RmsDepSearchRepository rmsDepSearchRepository;

    public RmsDepServiceImpl(RmsDepRepository rmsDepRepository, RmsDepMapper rmsDepMapper, RmsDepSearchRepository rmsDepSearchRepository) {
        this.rmsDepRepository = rmsDepRepository;
        this.rmsDepMapper = rmsDepMapper;
        this.rmsDepSearchRepository = rmsDepSearchRepository;
    }

    /**
     * Save a rmsDep.
     *
     * @param rmsDepDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RmsDepDTO save(RmsDepDTO rmsDepDTO) {
        log.debug("Request to save RmsDep : {}", rmsDepDTO);

        RmsDep rmsDep = rmsDepMapper.toEntity(rmsDepDTO);
        rmsDep = rmsDepRepository.save(rmsDep);
        RmsDepDTO result = rmsDepMapper.toDto(rmsDep);
        rmsDepSearchRepository.save(rmsDep);
        return result;
    }

    /**
     * Get all the rmsDeps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RmsDepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RmsDeps");
        return rmsDepRepository.findAll(pageable)
            .map(rmsDepMapper::toDto);
    }


    /**
     * Get one rmsDep by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RmsDepDTO> findOne(Long id) {
        log.debug("Request to get RmsDep : {}", id);
        return rmsDepRepository.findById(id)
            .map(rmsDepMapper::toDto);
    }

    /**
     * Delete the rmsDep by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RmsDep : {}", id);
        rmsDepRepository.deleteById(id);
        rmsDepSearchRepository.deleteById(id);
    }

    /**
     * Search for the rmsDep corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RmsDepDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of RmsDeps for query {}", query);
        return rmsDepSearchRepository.search(queryStringQuery(query), pageable)
            .map(rmsDepMapper::toDto);
    }
}
