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

import com.aerothinker.plandb.domain.PlanInfoStepData;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.PlanInfoStepDataRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepDataSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataCriteria;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepDataMapper;

/**
 * Service for executing complex queries for PlanInfoStepData entities in the database.
 * The main input is a {@link PlanInfoStepDataCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PlanInfoStepDataDTO} or a {@link Page} of {@link PlanInfoStepDataDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PlanInfoStepDataQueryService extends QueryService<PlanInfoStepData> {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepDataQueryService.class);

    private final PlanInfoStepDataRepository planInfoStepDataRepository;

    private final PlanInfoStepDataMapper planInfoStepDataMapper;

    private final PlanInfoStepDataSearchRepository planInfoStepDataSearchRepository;

    public PlanInfoStepDataQueryService(PlanInfoStepDataRepository planInfoStepDataRepository, PlanInfoStepDataMapper planInfoStepDataMapper, PlanInfoStepDataSearchRepository planInfoStepDataSearchRepository) {
        this.planInfoStepDataRepository = planInfoStepDataRepository;
        this.planInfoStepDataMapper = planInfoStepDataMapper;
        this.planInfoStepDataSearchRepository = planInfoStepDataSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PlanInfoStepDataDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PlanInfoStepDataDTO> findByCriteria(PlanInfoStepDataCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PlanInfoStepData> specification = createSpecification(criteria);
        return planInfoStepDataMapper.toDto(planInfoStepDataRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PlanInfoStepDataDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDataDTO> findByCriteria(PlanInfoStepDataCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PlanInfoStepData> specification = createSpecification(criteria);
        return planInfoStepDataRepository.findAll(specification, page)
            .map(planInfoStepDataMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PlanInfoStepDataCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PlanInfoStepData> specification = createSpecification(criteria);
        return planInfoStepDataRepository.count(specification);
    }

    /**
     * Function to convert PlanInfoStepDataCriteria to a {@link Specification}
     */
    private Specification<PlanInfoStepData> createSpecification(PlanInfoStepDataCriteria criteria) {
        Specification<PlanInfoStepData> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PlanInfoStepData_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PlanInfoStepData_.name));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), PlanInfoStepData_.sortString));
            }
            if (criteria.getDescString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescString(), PlanInfoStepData_.descString));
            }
            if (criteria.getJsonString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJsonString(), PlanInfoStepData_.jsonString));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), PlanInfoStepData_.remarks));
            }
            if (criteria.getAttachmentPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentPath(), PlanInfoStepData_.attachmentPath));
            }
            if (criteria.getAttachmentName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentName(), PlanInfoStepData_.attachmentName));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), PlanInfoStepData_.imageBlobName));
            }
            if (criteria.getUsingFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getUsingFlag(), PlanInfoStepData_.usingFlag));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), PlanInfoStepData_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), PlanInfoStepData_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), PlanInfoStepData_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), PlanInfoStepData_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), PlanInfoStepData_.updateTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), PlanInfoStepData_.verifyTime));
            }
            if (criteria.getVerifyNeed() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifyNeed(), PlanInfoStepData_.verifyNeed));
            }
            if (criteria.getVerifyOpinion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVerifyOpinion(), PlanInfoStepData_.verifyOpinion));
            }
            if (criteria.getViewCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getViewCount(), PlanInfoStepData_.viewCount));
            }
            if (criteria.getViewPermission() != null) {
                specification = specification.and(buildSpecification(criteria.getViewPermission(), PlanInfoStepData_.viewPermission));
            }
            if (criteria.getViewPermPersion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getViewPermPersion(), PlanInfoStepData_.viewPermPersion));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), PlanInfoStepData_.serialNumber));
            }
            if (criteria.getVersionNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVersionNumber(), PlanInfoStepData_.versionNumber));
            }
            if (criteria.getStepOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStepOrder(), PlanInfoStepData_.stepOrder));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(PlanInfoStepData_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getCreatedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedDepById(),
                    root -> root.join(PlanInfoStepData_.createdDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(PlanInfoStepData_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedDepById(),
                    root -> root.join(PlanInfoStepData_.modifiedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(PlanInfoStepData_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedDepById(),
                    root -> root.join(PlanInfoStepData_.verifiedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getPlanInfoStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoStepId(),
                    root -> root.join(PlanInfoStepData_.planInfoStep, JoinType.LEFT).get(PlanInfoStep_.id)));
            }
            if (criteria.getPlanInfoDataId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoDataId(),
                    root -> root.join(PlanInfoStepData_.planInfoData, JoinType.LEFT).get(PlanInfoData_.id)));
            }
        }
        return specification;
    }
}
