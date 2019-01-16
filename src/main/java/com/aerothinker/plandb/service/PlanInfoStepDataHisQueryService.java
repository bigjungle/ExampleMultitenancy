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

import com.aerothinker.plandb.domain.PlanInfoStepDataHis;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.PlanInfoStepDataHisRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepDataHisSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataHisCriteria;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataHisDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepDataHisMapper;

/**
 * Service for executing complex queries for PlanInfoStepDataHis entities in the database.
 * The main input is a {@link PlanInfoStepDataHisCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PlanInfoStepDataHisDTO} or a {@link Page} of {@link PlanInfoStepDataHisDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PlanInfoStepDataHisQueryService extends QueryService<PlanInfoStepDataHis> {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepDataHisQueryService.class);

    private final PlanInfoStepDataHisRepository planInfoStepDataHisRepository;

    private final PlanInfoStepDataHisMapper planInfoStepDataHisMapper;

    private final PlanInfoStepDataHisSearchRepository planInfoStepDataHisSearchRepository;

    public PlanInfoStepDataHisQueryService(PlanInfoStepDataHisRepository planInfoStepDataHisRepository, PlanInfoStepDataHisMapper planInfoStepDataHisMapper, PlanInfoStepDataHisSearchRepository planInfoStepDataHisSearchRepository) {
        this.planInfoStepDataHisRepository = planInfoStepDataHisRepository;
        this.planInfoStepDataHisMapper = planInfoStepDataHisMapper;
        this.planInfoStepDataHisSearchRepository = planInfoStepDataHisSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PlanInfoStepDataHisDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PlanInfoStepDataHisDTO> findByCriteria(PlanInfoStepDataHisCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PlanInfoStepDataHis> specification = createSpecification(criteria);
        return planInfoStepDataHisMapper.toDto(planInfoStepDataHisRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PlanInfoStepDataHisDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDataHisDTO> findByCriteria(PlanInfoStepDataHisCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PlanInfoStepDataHis> specification = createSpecification(criteria);
        return planInfoStepDataHisRepository.findAll(specification, page)
            .map(planInfoStepDataHisMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PlanInfoStepDataHisCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PlanInfoStepDataHis> specification = createSpecification(criteria);
        return planInfoStepDataHisRepository.count(specification);
    }

    /**
     * Function to convert PlanInfoStepDataHisCriteria to a {@link Specification}
     */
    private Specification<PlanInfoStepDataHis> createSpecification(PlanInfoStepDataHisCriteria criteria) {
        Specification<PlanInfoStepDataHis> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PlanInfoStepDataHis_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PlanInfoStepDataHis_.name));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), PlanInfoStepDataHis_.sortString));
            }
            if (criteria.getDescString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescString(), PlanInfoStepDataHis_.descString));
            }
            if (criteria.getJsonString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJsonString(), PlanInfoStepDataHis_.jsonString));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), PlanInfoStepDataHis_.remarks));
            }
            if (criteria.getAttachmentPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentPath(), PlanInfoStepDataHis_.attachmentPath));
            }
            if (criteria.getAttachmentName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentName(), PlanInfoStepDataHis_.attachmentName));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), PlanInfoStepDataHis_.imageBlobName));
            }
            if (criteria.getUsingFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getUsingFlag(), PlanInfoStepDataHis_.usingFlag));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), PlanInfoStepDataHis_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), PlanInfoStepDataHis_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), PlanInfoStepDataHis_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), PlanInfoStepDataHis_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), PlanInfoStepDataHis_.updateTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), PlanInfoStepDataHis_.verifyTime));
            }
            if (criteria.getVerifyNeed() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifyNeed(), PlanInfoStepDataHis_.verifyNeed));
            }
            if (criteria.getVerifyOpinion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVerifyOpinion(), PlanInfoStepDataHis_.verifyOpinion));
            }
            if (criteria.getViewCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getViewCount(), PlanInfoStepDataHis_.viewCount));
            }
            if (criteria.getViewPermission() != null) {
                specification = specification.and(buildSpecification(criteria.getViewPermission(), PlanInfoStepDataHis_.viewPermission));
            }
            if (criteria.getViewPermPersion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getViewPermPersion(), PlanInfoStepDataHis_.viewPermPersion));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), PlanInfoStepDataHis_.serialNumber));
            }
            if (criteria.getVersionNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVersionNumber(), PlanInfoStepDataHis_.versionNumber));
            }
            if (criteria.getStepOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStepOrder(), PlanInfoStepDataHis_.stepOrder));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(PlanInfoStepDataHis_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getCreatedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedDepById(),
                    root -> root.join(PlanInfoStepDataHis_.createdDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(PlanInfoStepDataHis_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedDepById(),
                    root -> root.join(PlanInfoStepDataHis_.modifiedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(PlanInfoStepDataHis_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedDepById(),
                    root -> root.join(PlanInfoStepDataHis_.verifiedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getPlanInfoStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoStepId(),
                    root -> root.join(PlanInfoStepDataHis_.planInfoStep, JoinType.LEFT).get(PlanInfoStep_.id)));
            }
            if (criteria.getPlanInfoDataHisId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoDataHisId(),
                    root -> root.join(PlanInfoStepDataHis_.planInfoDataHis, JoinType.LEFT).get(PlanInfoDataHis_.id)));
            }
        }
        return specification;
    }
}
