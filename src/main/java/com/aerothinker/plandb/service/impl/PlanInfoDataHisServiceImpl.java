package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.PlanInfoDataHisService;
import com.aerothinker.plandb.domain.PlanInfoDataHis;
import com.aerothinker.plandb.repository.PlanInfoDataHisRepository;
import com.aerothinker.plandb.repository.search.PlanInfoDataHisSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoDataHisDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoDataHisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PlanInfoDataHis.
 */
@Service
@Transactional
public class PlanInfoDataHisServiceImpl implements PlanInfoDataHisService {

    private final Logger log = LoggerFactory.getLogger(PlanInfoDataHisServiceImpl.class);

    private final PlanInfoDataHisRepository planInfoDataHisRepository;

    private final PlanInfoDataHisMapper planInfoDataHisMapper;

    private final PlanInfoDataHisSearchRepository planInfoDataHisSearchRepository;

    public PlanInfoDataHisServiceImpl(PlanInfoDataHisRepository planInfoDataHisRepository, PlanInfoDataHisMapper planInfoDataHisMapper, PlanInfoDataHisSearchRepository planInfoDataHisSearchRepository) {
        this.planInfoDataHisRepository = planInfoDataHisRepository;
        this.planInfoDataHisMapper = planInfoDataHisMapper;
        this.planInfoDataHisSearchRepository = planInfoDataHisSearchRepository;
    }

    /**
     * Save a planInfoDataHis.
     *
     * @param planInfoDataHisDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanInfoDataHisDTO save(PlanInfoDataHisDTO planInfoDataHisDTO) {
        log.debug("Request to save PlanInfoDataHis : {}", planInfoDataHisDTO);

        PlanInfoDataHis planInfoDataHis = planInfoDataHisMapper.toEntity(planInfoDataHisDTO);
        planInfoDataHis = planInfoDataHisRepository.save(planInfoDataHis);
        PlanInfoDataHisDTO result = planInfoDataHisMapper.toDto(planInfoDataHis);
        planInfoDataHisSearchRepository.save(planInfoDataHis);
        return result;
    }

    /**
     * Get all the planInfoDataHis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoDataHisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanInfoDataHis");
        return planInfoDataHisRepository.findAll(pageable)
            .map(planInfoDataHisMapper::toDto);
    }


    /**
     * Get one planInfoDataHis by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanInfoDataHisDTO> findOne(Long id) {
        log.debug("Request to get PlanInfoDataHis : {}", id);
        return planInfoDataHisRepository.findById(id)
            .map(planInfoDataHisMapper::toDto);
    }

    /**
     * Delete the planInfoDataHis by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanInfoDataHis : {}", id);
        planInfoDataHisRepository.deleteById(id);
        planInfoDataHisSearchRepository.deleteById(id);
    }

    /**
     * Search for the planInfoDataHis corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoDataHisDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PlanInfoDataHis for query {}", query);
        return planInfoDataHisSearchRepository.search(queryStringQuery(query), pageable)
            .map(planInfoDataHisMapper::toDto);
    }
}
