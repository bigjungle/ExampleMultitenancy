package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.PlanInfoAtchService;
import com.aerothinker.plandb.domain.PlanInfoAtch;
import com.aerothinker.plandb.repository.PlanInfoAtchRepository;
import com.aerothinker.plandb.repository.search.PlanInfoAtchSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoAtchDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoAtchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PlanInfoAtch.
 */
@Service
@Transactional
public class PlanInfoAtchServiceImpl implements PlanInfoAtchService {

    private final Logger log = LoggerFactory.getLogger(PlanInfoAtchServiceImpl.class);

    private final PlanInfoAtchRepository planInfoAtchRepository;

    private final PlanInfoAtchMapper planInfoAtchMapper;

    private final PlanInfoAtchSearchRepository planInfoAtchSearchRepository;

    public PlanInfoAtchServiceImpl(PlanInfoAtchRepository planInfoAtchRepository, PlanInfoAtchMapper planInfoAtchMapper, PlanInfoAtchSearchRepository planInfoAtchSearchRepository) {
        this.planInfoAtchRepository = planInfoAtchRepository;
        this.planInfoAtchMapper = planInfoAtchMapper;
        this.planInfoAtchSearchRepository = planInfoAtchSearchRepository;
    }

    /**
     * Save a planInfoAtch.
     *
     * @param planInfoAtchDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanInfoAtchDTO save(PlanInfoAtchDTO planInfoAtchDTO) {
        log.debug("Request to save PlanInfoAtch : {}", planInfoAtchDTO);

        PlanInfoAtch planInfoAtch = planInfoAtchMapper.toEntity(planInfoAtchDTO);
        planInfoAtch = planInfoAtchRepository.save(planInfoAtch);
        PlanInfoAtchDTO result = planInfoAtchMapper.toDto(planInfoAtch);
        planInfoAtchSearchRepository.save(planInfoAtch);
        return result;
    }

    /**
     * Get all the planInfoAtches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoAtchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanInfoAtches");
        return planInfoAtchRepository.findAll(pageable)
            .map(planInfoAtchMapper::toDto);
    }


    /**
     * Get one planInfoAtch by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanInfoAtchDTO> findOne(Long id) {
        log.debug("Request to get PlanInfoAtch : {}", id);
        return planInfoAtchRepository.findById(id)
            .map(planInfoAtchMapper::toDto);
    }

    /**
     * Delete the planInfoAtch by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanInfoAtch : {}", id);
        planInfoAtchRepository.deleteById(id);
        planInfoAtchSearchRepository.deleteById(id);
    }

    /**
     * Search for the planInfoAtch corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoAtchDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PlanInfoAtches for query {}", query);
        return planInfoAtchSearchRepository.search(queryStringQuery(query), pageable)
            .map(planInfoAtchMapper::toDto);
    }
}
