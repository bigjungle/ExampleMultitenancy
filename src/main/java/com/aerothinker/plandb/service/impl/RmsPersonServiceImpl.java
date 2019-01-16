package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.RmsPersonService;
import com.aerothinker.plandb.domain.RmsPerson;
import com.aerothinker.plandb.repository.RmsPersonRepository;
import com.aerothinker.plandb.repository.search.RmsPersonSearchRepository;
import com.aerothinker.plandb.service.dto.RmsPersonDTO;
import com.aerothinker.plandb.service.mapper.RmsPersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing RmsPerson.
 */
@Service
@Transactional
public class RmsPersonServiceImpl implements RmsPersonService {

    private final Logger log = LoggerFactory.getLogger(RmsPersonServiceImpl.class);

    private final RmsPersonRepository rmsPersonRepository;

    private final RmsPersonMapper rmsPersonMapper;

    private final RmsPersonSearchRepository rmsPersonSearchRepository;

    public RmsPersonServiceImpl(RmsPersonRepository rmsPersonRepository, RmsPersonMapper rmsPersonMapper, RmsPersonSearchRepository rmsPersonSearchRepository) {
        this.rmsPersonRepository = rmsPersonRepository;
        this.rmsPersonMapper = rmsPersonMapper;
        this.rmsPersonSearchRepository = rmsPersonSearchRepository;
    }

    /**
     * Save a rmsPerson.
     *
     * @param rmsPersonDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RmsPersonDTO save(RmsPersonDTO rmsPersonDTO) {
        log.debug("Request to save RmsPerson : {}", rmsPersonDTO);

        RmsPerson rmsPerson = rmsPersonMapper.toEntity(rmsPersonDTO);
        rmsPerson = rmsPersonRepository.save(rmsPerson);
        RmsPersonDTO result = rmsPersonMapper.toDto(rmsPerson);
        rmsPersonSearchRepository.save(rmsPerson);
        return result;
    }

    /**
     * Get all the rmsPeople.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RmsPersonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RmsPeople");
        return rmsPersonRepository.findAll(pageable)
            .map(rmsPersonMapper::toDto);
    }


    /**
     * Get one rmsPerson by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RmsPersonDTO> findOne(Long id) {
        log.debug("Request to get RmsPerson : {}", id);
        return rmsPersonRepository.findById(id)
            .map(rmsPersonMapper::toDto);
    }

    /**
     * Delete the rmsPerson by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RmsPerson : {}", id);
        rmsPersonRepository.deleteById(id);
        rmsPersonSearchRepository.deleteById(id);
    }

    /**
     * Search for the rmsPerson corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RmsPersonDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of RmsPeople for query {}", query);
        return rmsPersonSearchRepository.search(queryStringQuery(query), pageable)
            .map(rmsPersonMapper::toDto);
    }
}
