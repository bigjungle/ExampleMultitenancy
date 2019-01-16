package com.aerothinker.plandb.service.impl;

import com.aerothinker.plandb.service.PlanInfoService;
import com.aerothinker.plandb.domain.PlanInfo;
import com.aerothinker.plandb.repository.PlanInfoRepository;
import com.aerothinker.plandb.repository.search.PlanInfoSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing PlanInfo.
 */
@Service
@Transactional
public class PlanInfoServiceImpl implements PlanInfoService {

    private final Logger log = LoggerFactory.getLogger(PlanInfoServiceImpl.class);

    private final PlanInfoRepository planInfoRepository;

    private final PlanInfoMapper planInfoMapper;

    private final PlanInfoSearchRepository planInfoSearchRepository;

    public PlanInfoServiceImpl(PlanInfoRepository planInfoRepository, PlanInfoMapper planInfoMapper, PlanInfoSearchRepository planInfoSearchRepository) {
        this.planInfoRepository = planInfoRepository;
        this.planInfoMapper = planInfoMapper;
        this.planInfoSearchRepository = planInfoSearchRepository;
    }

    /**
     * Save a planInfo.
     *
     * @param planInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanInfoDTO save(PlanInfoDTO planInfoDTO) {
        log.debug("Request to save PlanInfo : {}", planInfoDTO);

        PlanInfo planInfo = planInfoMapper.toEntity(planInfoDTO);
        planInfo = planInfoRepository.save(planInfo);
        PlanInfoDTO result = planInfoMapper.toDto(planInfo);
        planInfoSearchRepository.save(planInfo);
        return result;
    }

    /**
     * Get all the planInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanInfos");
        return planInfoRepository.findAll(pageable)
            .map(planInfoMapper::toDto);
    }


    /**
     * Get one planInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanInfoDTO> findOne(Long id) {
        log.debug("Request to get PlanInfo : {}", id);
        return planInfoRepository.findById(id)
            .map(planInfoMapper::toDto);
    }

    /**
     * Delete the planInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanInfo : {}", id);
        planInfoRepository.deleteById(id);
        planInfoSearchRepository.deleteById(id);
    }

    /**
     * Search for the planInfo corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanInfoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PlanInfos for query {}", query);
        return planInfoSearchRepository.search(queryStringQuery(query), pageable)
            .map(planInfoMapper::toDto);
    }
}
