package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.PlanInfoStepDataAtchHisService;
import com.aerothinker.plandb.domain.PlanInfoStepDataAtchHis;
import com.aerothinker.plandb.repository.PlanInfoStepDataAtchHisRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepDataAtchHisSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchHisDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepDataAtchHisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PlanInfoStepDataAtchHis.
 */
@Service
@Transactional
public class PlanInfoStepDataAtchHisServiceImpl implements PlanInfoStepDataAtchHisService {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepDataAtchHisServiceImpl.class);

    private final PlanInfoStepDataAtchHisRepository planInfoStepDataAtchHisRepository;

    private final PlanInfoStepDataAtchHisMapper planInfoStepDataAtchHisMapper;

    private final PlanInfoStepDataAtchHisSearchRepository planInfoStepDataAtchHisSearchRepository;

    public PlanInfoStepDataAtchHisServiceImpl(PlanInfoStepDataAtchHisRepository planInfoStepDataAtchHisRepository, PlanInfoStepDataAtchHisMapper planInfoStepDataAtchHisMapper, PlanInfoStepDataAtchHisSearchRepository planInfoStepDataAtchHisSearchRepository) {
        this.planInfoStepDataAtchHisRepository = planInfoStepDataAtchHisRepository;
        this.planInfoStepDataAtchHisMapper = planInfoStepDataAtchHisMapper;
        this.planInfoStepDataAtchHisSearchRepository = planInfoStepDataAtchHisSearchRepository;
    }

    /**
     * Save a planInfoStepDataAtchHis.
     *
     * @param planInfoStepDataAtchHisDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanInfoStepDataAtchHisDTO save(PlanInfoStepDataAtchHisDTO planInfoStepDataAtchHisDTO) {
        log.debug("Request to save PlanInfoStepDataAtchHis : {}", planInfoStepDataAtchHisDTO);

        PlanInfoStepDataAtchHis planInfoStepDataAtchHis = planInfoStepDataAtchHisMapper.toEntity(planInfoStepDataAtchHisDTO);
        planInfoStepDataAtchHis = planInfoStepDataAtchHisRepository.save(planInfoStepDataAtchHis);
        PlanInfoStepDataAtchHisDTO result = planInfoStepDataAtchHisMapper.toDto(planInfoStepDataAtchHis);
        planInfoStepDataAtchHisSearchRepository.save(planInfoStepDataAtchHis);
        return result;
    }

    /**
     * Get all the planInfoStepDataAtchHis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDataAtchHisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanInfoStepDataAtchHis");
        return planInfoStepDataAtchHisRepository.findAll(pageable)
            .map(planInfoStepDataAtchHisMapper::toDto);
    }


    /**
     * Get one planInfoStepDataAtchHis by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanInfoStepDataAtchHisDTO> findOne(Long id) {
        log.debug("Request to get PlanInfoStepDataAtchHis : {}", id);
        return planInfoStepDataAtchHisRepository.findById(id)
            .map(planInfoStepDataAtchHisMapper::toDto);
    }

    /**
     * Delete the planInfoStepDataAtchHis by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanInfoStepDataAtchHis : {}", id);
        planInfoStepDataAtchHisRepository.deleteById(id);
        planInfoStepDataAtchHisSearchRepository.deleteById(id);
    }

    /**
     * Search for the planInfoStepDataAtchHis corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDataAtchHisDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PlanInfoStepDataAtchHis for query {}", query);
        return planInfoStepDataAtchHisSearchRepository.search(queryStringQuery(query), pageable)
            .map(planInfoStepDataAtchHisMapper::toDto);
    }
}
