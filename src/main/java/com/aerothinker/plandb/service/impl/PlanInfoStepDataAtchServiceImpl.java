package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.PlanInfoStepDataAtchService;
import com.aerothinker.plandb.domain.PlanInfoStepDataAtch;
import com.aerothinker.plandb.repository.PlanInfoStepDataAtchRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepDataAtchSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepDataAtchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PlanInfoStepDataAtch.
 */
@Service
@Transactional
public class PlanInfoStepDataAtchServiceImpl implements PlanInfoStepDataAtchService {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepDataAtchServiceImpl.class);

    private final PlanInfoStepDataAtchRepository planInfoStepDataAtchRepository;

    private final PlanInfoStepDataAtchMapper planInfoStepDataAtchMapper;

    private final PlanInfoStepDataAtchSearchRepository planInfoStepDataAtchSearchRepository;

    public PlanInfoStepDataAtchServiceImpl(PlanInfoStepDataAtchRepository planInfoStepDataAtchRepository, PlanInfoStepDataAtchMapper planInfoStepDataAtchMapper, PlanInfoStepDataAtchSearchRepository planInfoStepDataAtchSearchRepository) {
        this.planInfoStepDataAtchRepository = planInfoStepDataAtchRepository;
        this.planInfoStepDataAtchMapper = planInfoStepDataAtchMapper;
        this.planInfoStepDataAtchSearchRepository = planInfoStepDataAtchSearchRepository;
    }

    /**
     * Save a planInfoStepDataAtch.
     *
     * @param planInfoStepDataAtchDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanInfoStepDataAtchDTO save(PlanInfoStepDataAtchDTO planInfoStepDataAtchDTO) {
        log.debug("Request to save PlanInfoStepDataAtch : {}", planInfoStepDataAtchDTO);

        PlanInfoStepDataAtch planInfoStepDataAtch = planInfoStepDataAtchMapper.toEntity(planInfoStepDataAtchDTO);
        planInfoStepDataAtch = planInfoStepDataAtchRepository.save(planInfoStepDataAtch);
        PlanInfoStepDataAtchDTO result = planInfoStepDataAtchMapper.toDto(planInfoStepDataAtch);
        planInfoStepDataAtchSearchRepository.save(planInfoStepDataAtch);
        return result;
    }

    /**
     * Get all the planInfoStepDataAtches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDataAtchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanInfoStepDataAtches");
        return planInfoStepDataAtchRepository.findAll(pageable)
            .map(planInfoStepDataAtchMapper::toDto);
    }


    /**
     * Get one planInfoStepDataAtch by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanInfoStepDataAtchDTO> findOne(Long id) {
        log.debug("Request to get PlanInfoStepDataAtch : {}", id);
        return planInfoStepDataAtchRepository.findById(id)
            .map(planInfoStepDataAtchMapper::toDto);
    }

    /**
     * Delete the planInfoStepDataAtch by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanInfoStepDataAtch : {}", id);
        planInfoStepDataAtchRepository.deleteById(id);
        planInfoStepDataAtchSearchRepository.deleteById(id);
    }

    /**
     * Search for the planInfoStepDataAtch corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDataAtchDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PlanInfoStepDataAtches for query {}", query);
        return planInfoStepDataAtchSearchRepository.search(queryStringQuery(query), pageable)
            .map(planInfoStepDataAtchMapper::toDto);
    }
}
