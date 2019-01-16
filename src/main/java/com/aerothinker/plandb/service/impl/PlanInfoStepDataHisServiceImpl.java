package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.PlanInfoStepDataHisService;
import com.aerothinker.plandb.domain.PlanInfoStepDataHis;
import com.aerothinker.plandb.repository.PlanInfoStepDataHisRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepDataHisSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataHisDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepDataHisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PlanInfoStepDataHis.
 */
@Service
@Transactional
public class PlanInfoStepDataHisServiceImpl implements PlanInfoStepDataHisService {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepDataHisServiceImpl.class);

    private final PlanInfoStepDataHisRepository planInfoStepDataHisRepository;

    private final PlanInfoStepDataHisMapper planInfoStepDataHisMapper;

    private final PlanInfoStepDataHisSearchRepository planInfoStepDataHisSearchRepository;

    public PlanInfoStepDataHisServiceImpl(PlanInfoStepDataHisRepository planInfoStepDataHisRepository, PlanInfoStepDataHisMapper planInfoStepDataHisMapper, PlanInfoStepDataHisSearchRepository planInfoStepDataHisSearchRepository) {
        this.planInfoStepDataHisRepository = planInfoStepDataHisRepository;
        this.planInfoStepDataHisMapper = planInfoStepDataHisMapper;
        this.planInfoStepDataHisSearchRepository = planInfoStepDataHisSearchRepository;
    }

    /**
     * Save a planInfoStepDataHis.
     *
     * @param planInfoStepDataHisDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanInfoStepDataHisDTO save(PlanInfoStepDataHisDTO planInfoStepDataHisDTO) {
        log.debug("Request to save PlanInfoStepDataHis : {}", planInfoStepDataHisDTO);

        PlanInfoStepDataHis planInfoStepDataHis = planInfoStepDataHisMapper.toEntity(planInfoStepDataHisDTO);
        planInfoStepDataHis = planInfoStepDataHisRepository.save(planInfoStepDataHis);
        PlanInfoStepDataHisDTO result = planInfoStepDataHisMapper.toDto(planInfoStepDataHis);
        planInfoStepDataHisSearchRepository.save(planInfoStepDataHis);
        return result;
    }

    /**
     * Get all the planInfoStepDataHis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDataHisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanInfoStepDataHis");
        return planInfoStepDataHisRepository.findAll(pageable)
            .map(planInfoStepDataHisMapper::toDto);
    }


    /**
     * Get one planInfoStepDataHis by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanInfoStepDataHisDTO> findOne(Long id) {
        log.debug("Request to get PlanInfoStepDataHis : {}", id);
        return planInfoStepDataHisRepository.findById(id)
            .map(planInfoStepDataHisMapper::toDto);
    }

    /**
     * Delete the planInfoStepDataHis by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanInfoStepDataHis : {}", id);
        planInfoStepDataHisRepository.deleteById(id);
        planInfoStepDataHisSearchRepository.deleteById(id);
    }

    /**
     * Search for the planInfoStepDataHis corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDataHisDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PlanInfoStepDataHis for query {}", query);
        return planInfoStepDataHisSearchRepository.search(queryStringQuery(query), pageable)
            .map(planInfoStepDataHisMapper::toDto);
    }
}
