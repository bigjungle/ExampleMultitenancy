package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.PlanInfoDataAtchHisService;
import com.aerothinker.plandb.domain.PlanInfoDataAtchHis;
import com.aerothinker.plandb.repository.PlanInfoDataAtchHisRepository;
import com.aerothinker.plandb.repository.search.PlanInfoDataAtchHisSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchHisDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoDataAtchHisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PlanInfoDataAtchHis.
 */
@Service
@Transactional
public class PlanInfoDataAtchHisServiceImpl implements PlanInfoDataAtchHisService {

    private final Logger log = LoggerFactory.getLogger(PlanInfoDataAtchHisServiceImpl.class);

    private final PlanInfoDataAtchHisRepository planInfoDataAtchHisRepository;

    private final PlanInfoDataAtchHisMapper planInfoDataAtchHisMapper;

    private final PlanInfoDataAtchHisSearchRepository planInfoDataAtchHisSearchRepository;

    public PlanInfoDataAtchHisServiceImpl(PlanInfoDataAtchHisRepository planInfoDataAtchHisRepository, PlanInfoDataAtchHisMapper planInfoDataAtchHisMapper, PlanInfoDataAtchHisSearchRepository planInfoDataAtchHisSearchRepository) {
        this.planInfoDataAtchHisRepository = planInfoDataAtchHisRepository;
        this.planInfoDataAtchHisMapper = planInfoDataAtchHisMapper;
        this.planInfoDataAtchHisSearchRepository = planInfoDataAtchHisSearchRepository;
    }

    /**
     * Save a planInfoDataAtchHis.
     *
     * @param planInfoDataAtchHisDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanInfoDataAtchHisDTO save(PlanInfoDataAtchHisDTO planInfoDataAtchHisDTO) {
        log.debug("Request to save PlanInfoDataAtchHis : {}", planInfoDataAtchHisDTO);

        PlanInfoDataAtchHis planInfoDataAtchHis = planInfoDataAtchHisMapper.toEntity(planInfoDataAtchHisDTO);
        planInfoDataAtchHis = planInfoDataAtchHisRepository.save(planInfoDataAtchHis);
        PlanInfoDataAtchHisDTO result = planInfoDataAtchHisMapper.toDto(planInfoDataAtchHis);
        planInfoDataAtchHisSearchRepository.save(planInfoDataAtchHis);
        return result;
    }

    /**
     * Get all the planInfoDataAtchHis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoDataAtchHisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanInfoDataAtchHis");
        return planInfoDataAtchHisRepository.findAll(pageable)
            .map(planInfoDataAtchHisMapper::toDto);
    }


    /**
     * Get one planInfoDataAtchHis by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanInfoDataAtchHisDTO> findOne(Long id) {
        log.debug("Request to get PlanInfoDataAtchHis : {}", id);
        return planInfoDataAtchHisRepository.findById(id)
            .map(planInfoDataAtchHisMapper::toDto);
    }

    /**
     * Delete the planInfoDataAtchHis by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanInfoDataAtchHis : {}", id);
        planInfoDataAtchHisRepository.deleteById(id);
        planInfoDataAtchHisSearchRepository.deleteById(id);
    }

    /**
     * Search for the planInfoDataAtchHis corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoDataAtchHisDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PlanInfoDataAtchHis for query {}", query);
        return planInfoDataAtchHisSearchRepository.search(queryStringQuery(query), pageable)
            .map(planInfoDataAtchHisMapper::toDto);
    }
}
