package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.PlanInfoStepAtchService;
import com.aerothinker.plandb.domain.PlanInfoStepAtch;
import com.aerothinker.plandb.repository.PlanInfoStepAtchRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepAtchSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoStepAtchDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepAtchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PlanInfoStepAtch.
 */
@Service
@Transactional
public class PlanInfoStepAtchServiceImpl implements PlanInfoStepAtchService {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepAtchServiceImpl.class);

    private final PlanInfoStepAtchRepository planInfoStepAtchRepository;

    private final PlanInfoStepAtchMapper planInfoStepAtchMapper;

    private final PlanInfoStepAtchSearchRepository planInfoStepAtchSearchRepository;

    public PlanInfoStepAtchServiceImpl(PlanInfoStepAtchRepository planInfoStepAtchRepository, PlanInfoStepAtchMapper planInfoStepAtchMapper, PlanInfoStepAtchSearchRepository planInfoStepAtchSearchRepository) {
        this.planInfoStepAtchRepository = planInfoStepAtchRepository;
        this.planInfoStepAtchMapper = planInfoStepAtchMapper;
        this.planInfoStepAtchSearchRepository = planInfoStepAtchSearchRepository;
    }

    /**
     * Save a planInfoStepAtch.
     *
     * @param planInfoStepAtchDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanInfoStepAtchDTO save(PlanInfoStepAtchDTO planInfoStepAtchDTO) {
        log.debug("Request to save PlanInfoStepAtch : {}", planInfoStepAtchDTO);

        PlanInfoStepAtch planInfoStepAtch = planInfoStepAtchMapper.toEntity(planInfoStepAtchDTO);
        planInfoStepAtch = planInfoStepAtchRepository.save(planInfoStepAtch);
        PlanInfoStepAtchDTO result = planInfoStepAtchMapper.toDto(planInfoStepAtch);
        planInfoStepAtchSearchRepository.save(planInfoStepAtch);
        return result;
    }

    /**
     * Get all the planInfoStepAtches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoStepAtchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanInfoStepAtches");
        return planInfoStepAtchRepository.findAll(pageable)
            .map(planInfoStepAtchMapper::toDto);
    }


    /**
     * Get one planInfoStepAtch by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanInfoStepAtchDTO> findOne(Long id) {
        log.debug("Request to get PlanInfoStepAtch : {}", id);
        return planInfoStepAtchRepository.findById(id)
            .map(planInfoStepAtchMapper::toDto);
    }

    /**
     * Delete the planInfoStepAtch by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanInfoStepAtch : {}", id);
        planInfoStepAtchRepository.deleteById(id);
        planInfoStepAtchSearchRepository.deleteById(id);
    }

    /**
     * Search for the planInfoStepAtch corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoStepAtchDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PlanInfoStepAtches for query {}", query);
        return planInfoStepAtchSearchRepository.search(queryStringQuery(query), pageable)
            .map(planInfoStepAtchMapper::toDto);
    }
}
