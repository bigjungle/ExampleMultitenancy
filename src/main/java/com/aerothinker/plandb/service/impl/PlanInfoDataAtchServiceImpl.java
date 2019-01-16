package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.PlanInfoDataAtchService;
import com.aerothinker.plandb.domain.PlanInfoDataAtch;
import com.aerothinker.plandb.repository.PlanInfoDataAtchRepository;
import com.aerothinker.plandb.repository.search.PlanInfoDataAtchSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoDataAtchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PlanInfoDataAtch.
 */
@Service
@Transactional
public class PlanInfoDataAtchServiceImpl implements PlanInfoDataAtchService {

    private final Logger log = LoggerFactory.getLogger(PlanInfoDataAtchServiceImpl.class);

    private final PlanInfoDataAtchRepository planInfoDataAtchRepository;

    private final PlanInfoDataAtchMapper planInfoDataAtchMapper;

    private final PlanInfoDataAtchSearchRepository planInfoDataAtchSearchRepository;

    public PlanInfoDataAtchServiceImpl(PlanInfoDataAtchRepository planInfoDataAtchRepository, PlanInfoDataAtchMapper planInfoDataAtchMapper, PlanInfoDataAtchSearchRepository planInfoDataAtchSearchRepository) {
        this.planInfoDataAtchRepository = planInfoDataAtchRepository;
        this.planInfoDataAtchMapper = planInfoDataAtchMapper;
        this.planInfoDataAtchSearchRepository = planInfoDataAtchSearchRepository;
    }

    /**
     * Save a planInfoDataAtch.
     *
     * @param planInfoDataAtchDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanInfoDataAtchDTO save(PlanInfoDataAtchDTO planInfoDataAtchDTO) {
        log.debug("Request to save PlanInfoDataAtch : {}", planInfoDataAtchDTO);

        PlanInfoDataAtch planInfoDataAtch = planInfoDataAtchMapper.toEntity(planInfoDataAtchDTO);
        planInfoDataAtch = planInfoDataAtchRepository.save(planInfoDataAtch);
        PlanInfoDataAtchDTO result = planInfoDataAtchMapper.toDto(planInfoDataAtch);
        planInfoDataAtchSearchRepository.save(planInfoDataAtch);
        return result;
    }

    /**
     * Get all the planInfoDataAtches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoDataAtchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanInfoDataAtches");
        return planInfoDataAtchRepository.findAll(pageable)
            .map(planInfoDataAtchMapper::toDto);
    }


    /**
     * Get one planInfoDataAtch by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanInfoDataAtchDTO> findOne(Long id) {
        log.debug("Request to get PlanInfoDataAtch : {}", id);
        return planInfoDataAtchRepository.findById(id)
            .map(planInfoDataAtchMapper::toDto);
    }

    /**
     * Delete the planInfoDataAtch by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanInfoDataAtch : {}", id);
        planInfoDataAtchRepository.deleteById(id);
        planInfoDataAtchSearchRepository.deleteById(id);
    }

    /**
     * Search for the planInfoDataAtch corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoDataAtchDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PlanInfoDataAtches for query {}", query);
        return planInfoDataAtchSearchRepository.search(queryStringQuery(query), pageable)
            .map(planInfoDataAtchMapper::toDto);
    }
}
