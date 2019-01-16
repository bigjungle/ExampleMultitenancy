package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.PlanInfoDataService;
import com.aerothinker.plandb.domain.PlanInfoData;
import com.aerothinker.plandb.repository.PlanInfoDataRepository;
import com.aerothinker.plandb.repository.search.PlanInfoDataSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoDataDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PlanInfoData.
 */
@Service
@Transactional
public class PlanInfoDataServiceImpl implements PlanInfoDataService {

    private final Logger log = LoggerFactory.getLogger(PlanInfoDataServiceImpl.class);

    private final PlanInfoDataRepository planInfoDataRepository;

    private final PlanInfoDataMapper planInfoDataMapper;

    private final PlanInfoDataSearchRepository planInfoDataSearchRepository;

    public PlanInfoDataServiceImpl(PlanInfoDataRepository planInfoDataRepository, PlanInfoDataMapper planInfoDataMapper, PlanInfoDataSearchRepository planInfoDataSearchRepository) {
        this.planInfoDataRepository = planInfoDataRepository;
        this.planInfoDataMapper = planInfoDataMapper;
        this.planInfoDataSearchRepository = planInfoDataSearchRepository;
    }

    /**
     * Save a planInfoData.
     *
     * @param planInfoDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanInfoDataDTO save(PlanInfoDataDTO planInfoDataDTO) {
        log.debug("Request to save PlanInfoData : {}", planInfoDataDTO);

        PlanInfoData planInfoData = planInfoDataMapper.toEntity(planInfoDataDTO);
        planInfoData = planInfoDataRepository.save(planInfoData);
        PlanInfoDataDTO result = planInfoDataMapper.toDto(planInfoData);
        planInfoDataSearchRepository.save(planInfoData);
        return result;
    }

    /**
     * Get all the planInfoData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanInfoData");
        return planInfoDataRepository.findAll(pageable)
            .map(planInfoDataMapper::toDto);
    }


    /**
     * Get one planInfoData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanInfoDataDTO> findOne(Long id) {
        log.debug("Request to get PlanInfoData : {}", id);
        return planInfoDataRepository.findById(id)
            .map(planInfoDataMapper::toDto);
    }

    /**
     * Delete the planInfoData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanInfoData : {}", id);
        planInfoDataRepository.deleteById(id);
        planInfoDataSearchRepository.deleteById(id);
    }

    /**
     * Search for the planInfoData corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoDataDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PlanInfoData for query {}", query);
        return planInfoDataSearchRepository.search(queryStringQuery(query), pageable)
            .map(planInfoDataMapper::toDto);
    }
}
