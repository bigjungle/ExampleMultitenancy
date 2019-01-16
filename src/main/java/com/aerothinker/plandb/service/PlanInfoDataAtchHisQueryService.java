package com.aerothinker.plandb.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.aerothinker.plandb.domain.PlanInfoDataAtchHis;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.PlanInfoDataAtchHisRepository;
import com.aerothinker.plandb.repository.search.PlanInfoDataAtchHisSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchHisCriteria;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchHisDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoDataAtchHisMapper;

/**
 * Service for executing complex queries for PlanInfoDataAtchHis entities in the database.
 * The main input is a {@link PlanInfoDataAtchHisCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PlanInfoDataAtchHisDTO} or a {@link Page} of {@link PlanInfoDataAtchHisDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PlanInfoDataAtchHisQueryService extends QueryService<PlanInfoDataAtchHis> {

    private final Logger log = LoggerFactory.getLogger(PlanInfoDataAtchHisQueryService.class);

    private final PlanInfoDataAtchHisRepository planInfoDataAtchHisRepository;

    private final PlanInfoDataAtchHisMapper planInfoDataAtchHisMapper;

    private final PlanInfoDataAtchHisSearchRepository planInfoDataAtchHisSearchRepository;

    public PlanInfoDataAtchHisQueryService(PlanInfoDataAtchHisRepository planInfoDataAtchHisRepository, PlanInfoDataAtchHisMapper planInfoDataAtchHisMapper, PlanInfoDataAtchHisSearchRepository planInfoDataAtchHisSearchRepository) {
        this.planInfoDataAtchHisRepository = planInfoDataAtchHisRepository;
        this.planInfoDataAtchHisMapper = planInfoDataAtchHisMapper;
        this.planInfoDataAtchHisSearchRepository = planInfoDataAtchHisSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PlanInfoDataAtchHisDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PlanInfoDataAtchHisDTO> findByCriteria(PlanInfoDataAtchHisCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PlanInfoDataAtchHis> specification = createSpecification(criteria);
        return planInfoDataAtchHisMapper.toDto(planInfoDataAtchHisRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PlanInfoDataAtchHisDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PlanInfoDataAtchHisDTO> findByCriteria(PlanInfoDataAtchHisCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PlanInfoDataAtchHis> specification = createSpecification(criteria);
        return planInfoDataAtchHisRepository.findAll(specification, page)
            .map(planInfoDataAtchHisMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PlanInfoDataAtchHisCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PlanInfoDataAtchHis> specification = createSpecification(criteria);
        return planInfoDataAtchHisRepository.count(specification);
    }

    /**
     * Function to convert PlanInfoDataAtchHisCriteria to a {@link Specification}
     */
    private Specification<PlanInfoDataAtchHis> createSpecification(PlanInfoDataAtchHisCriteria criteria) {
        Specification<PlanInfoDataAtchHis> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PlanInfoDataAtchHis_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PlanInfoDataAtchHis_.name));
            }
            if (criteria.getStoreDir() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreDir(), PlanInfoDataAtchHis_.storeDir));
            }
            if (criteria.getStoreName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreName(), PlanInfoDataAtchHis_.storeName));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), PlanInfoDataAtchHis_.sortString));
            }
            if (criteria.getFileType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileType(), PlanInfoDataAtchHis_.fileType));
            }
            if (criteria.getDeleteFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getDeleteFlag(), PlanInfoDataAtchHis_.deleteFlag));
            }
            if (criteria.getStoreType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreType(), PlanInfoDataAtchHis_.storeType));
            }
            if (criteria.getVer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVer(), PlanInfoDataAtchHis_.ver));
            }
            if (criteria.getEncryptedFlag() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEncryptedFlag(), PlanInfoDataAtchHis_.encryptedFlag));
            }
            if (criteria.getEncryptedType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEncryptedType(), PlanInfoDataAtchHis_.encryptedType));
            }
            if (criteria.getJsonString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJsonString(), PlanInfoDataAtchHis_.jsonString));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), PlanInfoDataAtchHis_.remarks));
            }
            if (criteria.getAttachmentPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentPath(), PlanInfoDataAtchHis_.attachmentPath));
            }
            if (criteria.getAttachmentName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentName(), PlanInfoDataAtchHis_.attachmentName));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), PlanInfoDataAtchHis_.imageBlobName));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), PlanInfoDataAtchHis_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), PlanInfoDataAtchHis_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), PlanInfoDataAtchHis_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), PlanInfoDataAtchHis_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), PlanInfoDataAtchHis_.updateTime));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(PlanInfoDataAtchHis_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(PlanInfoDataAtchHis_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getPlanInfoAtchId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoAtchId(),
                    root -> root.join(PlanInfoDataAtchHis_.planInfoAtch, JoinType.LEFT).get(PlanInfoAtch_.id)));
            }
            if (criteria.getPlanInfoDataHisId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoDataHisId(),
                    root -> root.join(PlanInfoDataAtchHis_.planInfoDataHis, JoinType.LEFT).get(PlanInfoDataHis_.id)));
            }
        }
        return specification;
    }
}
