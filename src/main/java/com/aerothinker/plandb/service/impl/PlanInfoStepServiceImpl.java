package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.PlanInfoStepService;
import com.aerothinker.plandb.domain.PlanInfoStep;
import com.aerothinker.plandb.repository.PlanInfoStepRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoStepDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PlanInfoStep.
 */
@Service
@Transactional
public class PlanInfoStepServiceImpl implements PlanInfoStepService {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepServiceImpl.class);

    private final PlanInfoStepRepository planInfoStepRepository;

    private final PlanInfoStepMapper planInfoStepMapper;

    private final PlanInfoStepSearchRepository planInfoStepSearchRepository;

    public PlanInfoStepServiceImpl(PlanInfoStepRepository planInfoStepRepository, PlanInfoStepMapper planInfoStepMapper, PlanInfoStepSearchRepository planInfoStepSearchRepository) {
        this.planInfoStepRepository = planInfoStepRepository;
        this.planInfoStepMapper = planInfoStepMapper;
        this.planInfoStepSearchRepository = planInfoStepSearchRepository;
    }

    /**
     * Save a planInfoStep.
     *
     * @param planInfoStepDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanInfoStepDTO save(PlanInfoStepDTO planInfoStepDTO) {
        log.debug("Request to save PlanInfoStep : {}", planInfoStepDTO);

        PlanInfoStep planInfoStep = planInfoStepMapper.toEntity(planInfoStepDTO);
        planInfoStep = planInfoStepRepository.save(planInfoStep);
        PlanInfoStepDTO result = planInfoStepMapper.toDto(planInfoStep);
        planInfoStepSearchRepository.save(planInfoStep);
        return result;
    }

    /**
     * Get all the planInfoSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanInfoSteps");
        return planInfoStepRepository.findAll(pageable)
            .map(planInfoStepMapper::toDto);
    }


    /**
     * Get one planInfoStep by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanInfoStepDTO> findOne(Long id) {
        log.debug("Request to get PlanInfoStep : {}", id);
        return planInfoStepRepository.findById(id)
            .map(planInfoStepMapper::toDto);
    }

    /**
     * Delete the planInfoStep by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanInfoStep : {}", id);
        planInfoStepRepository.deleteById(id);
        planInfoStepSearchRepository.deleteById(id);
    }

    /**
     * Search for the planInfoStep corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PlanInfoSteps for query {}", query);
        return planInfoStepSearchRepository.search(queryStringQuery(query), pageable)
            .map(planInfoStepMapper::toDto);
    }
}
