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

import com.aerothinker.plandb.domain.PlanInfoStep;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.PlanInfoStepRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoStepCriteria;
import com.aerothinker.plandb.service.dto.PlanInfoStepDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepMapper;

/**
 * Service for executing complex queries for PlanInfoStep entities in the database.
 * The main input is a {@link PlanInfoStepCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PlanInfoStepDTO} or a {@link Page} of {@link PlanInfoStepDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PlanInfoStepQueryService extends QueryService<PlanInfoStep> {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepQueryService.class);

    private final PlanInfoStepRepository planInfoStepRepository;

    private final PlanInfoStepMapper planInfoStepMapper;

    private final PlanInfoStepSearchRepository planInfoStepSearchRepository;

    public PlanInfoStepQueryService(PlanInfoStepRepository planInfoStepRepository, PlanInfoStepMapper planInfoStepMapper, PlanInfoStepSearchRepository planInfoStepSearchRepository) {
        this.planInfoStepRepository = planInfoStepRepository;
        this.planInfoStepMapper = planInfoStepMapper;
        this.planInfoStepSearchRepository = planInfoStepSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PlanInfoStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PlanInfoStepDTO> findByCriteria(PlanInfoStepCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PlanInfoStep> specification = createSpecification(criteria);
        return planInfoStepMapper.toDto(planInfoStepRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PlanInfoStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDTO> findByCriteria(PlanInfoStepCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PlanInfoStep> specification = createSpecification(criteria);
        return planInfoStepRepository.findAll(specification, page)
            .map(planInfoStepMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PlanInfoStepCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PlanInfoStep> specification = createSpecification(criteria);
        return planInfoStepRepository.count(specification);
    }

    /**
     * Function to convert PlanInfoStepCriteria to a {@link Specification}
     */
    private Specification<PlanInfoStep> createSpecification(PlanInfoStepCriteria criteria) {
        Specification<PlanInfoStep> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PlanInfoStep_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PlanInfoStep_.name));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), PlanInfoStep_.sortString));
            }
            if (criteria.getDescString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescString(), PlanInfoStep_.descString));
            }
            if (criteria.getJsonString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJsonString(), PlanInfoStep_.jsonString));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), PlanInfoStep_.remarks));
            }
            if (criteria.getAttachmentPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentPath(), PlanInfoStep_.attachmentPath));
            }
            if (criteria.getAttachmentName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentName(), PlanInfoStep_.attachmentName));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), PlanInfoStep_.imageBlobName));
            }
            if (criteria.getUsingFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getUsingFlag(), PlanInfoStep_.usingFlag));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), PlanInfoStep_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), PlanInfoStep_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), PlanInfoStep_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), PlanInfoStep_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), PlanInfoStep_.updateTime));
            }
            if (criteria.getStepOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStepOrder(), PlanInfoStep_.stepOrder));
            }
            if (criteria.getPublishedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPublishedTime(), PlanInfoStep_.publishedTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), PlanInfoStep_.verifyTime));
            }
            if (criteria.getVerifyNeed() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifyNeed(), PlanInfoStep_.verifyNeed));
            }
            if (criteria.getVerifyOpinion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVerifyOpinion(), PlanInfoStep_.verifyOpinion));
            }
            if (criteria.getViewCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getViewCount(), PlanInfoStep_.viewCount));
            }
            if (criteria.getViewPermission() != null) {
                specification = specification.and(buildSpecification(criteria.getViewPermission(), PlanInfoStep_.viewPermission));
            }
            if (criteria.getViewPermPersion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getViewPermPersion(), PlanInfoStep_.viewPermPersion));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), PlanInfoStep_.serialNumber));
            }
            if (criteria.getVersionNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVersionNumber(), PlanInfoStep_.versionNumber));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(PlanInfoStep_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getCreatedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedDepById(),
                    root -> root.join(PlanInfoStep_.createdDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(PlanInfoStep_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedDepById(),
                    root -> root.join(PlanInfoStep_.modifiedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(PlanInfoStep_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getPlanInfoId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoId(),
                    root -> root.join(PlanInfoStep_.planInfo, JoinType.LEFT).get(PlanInfo_.id)));
            }
        }
        return specification;
    }
}
