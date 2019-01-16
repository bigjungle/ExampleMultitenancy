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

import com.aerothinker.plandb.domain.PlanInfoAtch;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.PlanInfoAtchRepository;
import com.aerothinker.plandb.repository.search.PlanInfoAtchSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoAtchCriteria;
import com.aerothinker.plandb.service.dto.PlanInfoAtchDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoAtchMapper;

/**
 * Service for executing complex queries for PlanInfoAtch entities in the database.
 * The main input is a {@link PlanInfoAtchCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PlanInfoAtchDTO} or a {@link Page} of {@link PlanInfoAtchDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PlanInfoAtchQueryService extends QueryService<PlanInfoAtch> {

    private final Logger log = LoggerFactory.getLogger(PlanInfoAtchQueryService.class);

    private final PlanInfoAtchRepository planInfoAtchRepository;

    private final PlanInfoAtchMapper planInfoAtchMapper;

    private final PlanInfoAtchSearchRepository planInfoAtchSearchRepository;

    public PlanInfoAtchQueryService(PlanInfoAtchRepository planInfoAtchRepository, PlanInfoAtchMapper planInfoAtchMapper, PlanInfoAtchSearchRepository planInfoAtchSearchRepository) {
        this.planInfoAtchRepository = planInfoAtchRepository;
        this.planInfoAtchMapper = planInfoAtchMapper;
        this.planInfoAtchSearchRepository = planInfoAtchSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PlanInfoAtchDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PlanInfoAtchDTO> findByCriteria(PlanInfoAtchCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PlanInfoAtch> specification = createSpecification(criteria);
        return planInfoAtchMapper.toDto(planInfoAtchRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PlanInfoAtchDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PlanInfoAtchDTO> findByCriteria(PlanInfoAtchCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PlanInfoAtch> specification = createSpecification(criteria);
        return planInfoAtchRepository.findAll(specification, page)
            .map(planInfoAtchMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PlanInfoAtchCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PlanInfoAtch> specification = createSpecification(criteria);
        return planInfoAtchRepository.count(specification);
    }

    /**
     * Function to convert PlanInfoAtchCriteria to a {@link Specification}
     */
    private Specification<PlanInfoAtch> createSpecification(PlanInfoAtchCriteria criteria) {
        Specification<PlanInfoAtch> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PlanInfoAtch_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PlanInfoAtch_.name));
            }
            if (criteria.getStoreDir() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreDir(), PlanInfoAtch_.storeDir));
            }
            if (criteria.getStoreName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreName(), PlanInfoAtch_.storeName));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), PlanInfoAtch_.sortString));
            }
            if (criteria.getFileType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileType(), PlanInfoAtch_.fileType));
            }
            if (criteria.getDeleteFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getDeleteFlag(), PlanInfoAtch_.deleteFlag));
            }
            if (criteria.getStoreType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreType(), PlanInfoAtch_.storeType));
            }
            if (criteria.getVer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVer(), PlanInfoAtch_.ver));
            }
            if (criteria.getEncryptedFlag() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEncryptedFlag(), PlanInfoAtch_.encryptedFlag));
            }
            if (criteria.getEncryptedType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEncryptedType(), PlanInfoAtch_.encryptedType));
            }
            if (criteria.getJsonString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJsonString(), PlanInfoAtch_.jsonString));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), PlanInfoAtch_.remarks));
            }
            if (criteria.getAttachmentPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentPath(), PlanInfoAtch_.attachmentPath));
            }
            if (criteria.getAttachmentName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentName(), PlanInfoAtch_.attachmentName));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), PlanInfoAtch_.imageBlobName));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), PlanInfoAtch_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), PlanInfoAtch_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), PlanInfoAtch_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), PlanInfoAtch_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), PlanInfoAtch_.updateTime));
            }
            if (criteria.getPublishedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPublishedTime(), PlanInfoAtch_.publishedTime));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(PlanInfoAtch_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(PlanInfoAtch_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getPlanInfoId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoId(),
                    root -> root.join(PlanInfoAtch_.planInfo, JoinType.LEFT).get(PlanInfo_.id)));
            }
        }
        return specification;
    }
}
