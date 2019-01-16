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

import com.aerothinker.plandb.domain.PlanInfoDataAtch;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.PlanInfoDataAtchRepository;
import com.aerothinker.plandb.repository.search.PlanInfoDataAtchSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchCriteria;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoDataAtchMapper;

/**
 * Service for executing complex queries for PlanInfoDataAtch entities in the database.
 * The main input is a {@link PlanInfoDataAtchCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PlanInfoDataAtchDTO} or a {@link Page} of {@link PlanInfoDataAtchDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PlanInfoDataAtchQueryService extends QueryService<PlanInfoDataAtch> {

    private final Logger log = LoggerFactory.getLogger(PlanInfoDataAtchQueryService.class);

    private final PlanInfoDataAtchRepository planInfoDataAtchRepository;

    private final PlanInfoDataAtchMapper planInfoDataAtchMapper;

    private final PlanInfoDataAtchSearchRepository planInfoDataAtchSearchRepository;

    public PlanInfoDataAtchQueryService(PlanInfoDataAtchRepository planInfoDataAtchRepository, PlanInfoDataAtchMapper planInfoDataAtchMapper, PlanInfoDataAtchSearchRepository planInfoDataAtchSearchRepository) {
        this.planInfoDataAtchRepository = planInfoDataAtchRepository;
        this.planInfoDataAtchMapper = planInfoDataAtchMapper;
        this.planInfoDataAtchSearchRepository = planInfoDataAtchSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PlanInfoDataAtchDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PlanInfoDataAtchDTO> findByCriteria(PlanInfoDataAtchCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PlanInfoDataAtch> specification = createSpecification(criteria);
        return planInfoDataAtchMapper.toDto(planInfoDataAtchRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PlanInfoDataAtchDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PlanInfoDataAtchDTO> findByCriteria(PlanInfoDataAtchCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PlanInfoDataAtch> specification = createSpecification(criteria);
        return planInfoDataAtchRepository.findAll(specification, page)
            .map(planInfoDataAtchMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PlanInfoDataAtchCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PlanInfoDataAtch> specification = createSpecification(criteria);
        return planInfoDataAtchRepository.count(specification);
    }

    /**
     * Function to convert PlanInfoDataAtchCriteria to a {@link Specification}
     */
    private Specification<PlanInfoDataAtch> createSpecification(PlanInfoDataAtchCriteria criteria) {
        Specification<PlanInfoDataAtch> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PlanInfoDataAtch_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PlanInfoDataAtch_.name));
            }
            if (criteria.getStoreDir() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreDir(), PlanInfoDataAtch_.storeDir));
            }
            if (criteria.getStoreName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreName(), PlanInfoDataAtch_.storeName));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), PlanInfoDataAtch_.sortString));
            }
            if (criteria.getFileType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileType(), PlanInfoDataAtch_.fileType));
            }
            if (criteria.getDeleteFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getDeleteFlag(), PlanInfoDataAtch_.deleteFlag));
            }
            if (criteria.getStoreType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreType(), PlanInfoDataAtch_.storeType));
            }
            if (criteria.getVer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVer(), PlanInfoDataAtch_.ver));
            }
            if (criteria.getEncryptedFlag() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEncryptedFlag(), PlanInfoDataAtch_.encryptedFlag));
            }
            if (criteria.getEncryptedType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEncryptedType(), PlanInfoDataAtch_.encryptedType));
            }
            if (criteria.getJsonString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJsonString(), PlanInfoDataAtch_.jsonString));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), PlanInfoDataAtch_.remarks));
            }
            if (criteria.getAttachmentPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentPath(), PlanInfoDataAtch_.attachmentPath));
            }
            if (criteria.getAttachmentName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentName(), PlanInfoDataAtch_.attachmentName));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), PlanInfoDataAtch_.imageBlobName));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), PlanInfoDataAtch_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), PlanInfoDataAtch_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), PlanInfoDataAtch_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), PlanInfoDataAtch_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), PlanInfoDataAtch_.updateTime));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(PlanInfoDataAtch_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(PlanInfoDataAtch_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getPlanInfoAtchId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoAtchId(),
                    root -> root.join(PlanInfoDataAtch_.planInfoAtch, JoinType.LEFT).get(PlanInfoAtch_.id)));
            }
            if (criteria.getPlanInfoDataId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoDataId(),
                    root -> root.join(PlanInfoDataAtch_.planInfoData, JoinType.LEFT).get(PlanInfoData_.id)));
            }
        }
        return specification;
    }
}
