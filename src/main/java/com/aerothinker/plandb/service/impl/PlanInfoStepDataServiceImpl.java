package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.PlanInfoStepDataService;
import com.aerothinker.plandb.domain.PlanInfoStepData;
import com.aerothinker.plandb.repository.PlanInfoStepDataRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepDataSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PlanInfoStepData.
 */
@Service
@Transactional
public class PlanInfoStepDataServiceImpl implements PlanInfoStepDataService {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepDataServiceImpl.class);

    private final PlanInfoStepDataRepository planInfoStepDataRepository;

    private final PlanInfoStepDataMapper planInfoStepDataMapper;

    private final PlanInfoStepDataSearchRepository planInfoStepDataSearchRepository;

    public PlanInfoStepDataServiceImpl(PlanInfoStepDataRepository planInfoStepDataRepository, PlanInfoStepDataMapper planInfoStepDataMapper, PlanInfoStepDataSearchRepository planInfoStepDataSearchRepository) {
        this.planInfoStepDataRepository = planInfoStepDataRepository;
        this.planInfoStepDataMapper = planInfoStepDataMapper;
        this.planInfoStepDataSearchRepository = planInfoStepDataSearchRepository;
    }

    /**
     * Save a planInfoStepData.
     *
     * @param planInfoStepDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanInfoStepDataDTO save(PlanInfoStepDataDTO planInfoStepDataDTO) {
        log.debug("Request to save PlanInfoStepData : {}", planInfoStepDataDTO);

        PlanInfoStepData planInfoStepData = planInfoStepDataMapper.toEntity(planInfoStepDataDTO);
        planInfoStepData = planInfoStepDataRepository.save(planInfoStepData);
        PlanInfoStepDataDTO result = planInfoStepDataMapper.toDto(planInfoStepData);
        planInfoStepDataSearchRepository.save(planInfoStepData);
        return result;
    }

    /**
     * Get all the planInfoStepData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanInfoStepData");
        return planInfoStepDataRepository.findAll(pageable)
            .map(planInfoStepDataMapper::toDto);
    }


    /**
     * Get one planInfoStepData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanInfoStepDataDTO> findOne(Long id) {
        log.debug("Request to get PlanInfoStepData : {}", id);
        return planInfoStepDataRepository.findById(id)
            .map(planInfoStepDataMapper::toDto);
    }

    /**
     * Delete the planInfoStepData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanInfoStepData : {}", id);
        planInfoStepDataRepository.deleteById(id);
        planInfoStepDataSearchRepository.deleteById(id);
    }

    /**
     * Search for the planInfoStepData corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDataDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PlanInfoStepData for query {}", query);
        return planInfoStepDataSearchRepository.search(queryStringQuery(query), pageable)
            .map(planInfoStepDataMapper::toDto);
    }
}
