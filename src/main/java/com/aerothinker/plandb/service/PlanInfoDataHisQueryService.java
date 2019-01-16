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

import com.aerothinker.plandb.domain.PlanInfoDataHis;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.PlanInfoDataHisRepository;
import com.aerothinker.plandb.repository.search.PlanInfoDataHisSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoDataHisCriteria;
import com.aerothinker.plandb.service.dto.PlanInfoDataHisDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoDataHisMapper;

/**
 * Service for executing complex queries for PlanInfoDataHis entities in the database.
 * The main input is a {@link PlanInfoDataHisCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PlanInfoDataHisDTO} or a {@link Page} of {@link PlanInfoDataHisDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PlanInfoDataHisQueryService extends QueryService<PlanInfoDataHis> {

    private final Logger log = LoggerFactory.getLogger(PlanInfoDataHisQueryService.class);

    private final PlanInfoDataHisRepository planInfoDataHisRepository;

    private final PlanInfoDataHisMapper planInfoDataHisMapper;

    private final PlanInfoDataHisSearchRepository planInfoDataHisSearchRepository;

    public PlanInfoDataHisQueryService(PlanInfoDataHisRepository planInfoDataHisRepository, PlanInfoDataHisMapper planInfoDataHisMapper, PlanInfoDataHisSearchRepository planInfoDataHisSearchRepository) {
        this.planInfoDataHisRepository = planInfoDataHisRepository;
        this.planInfoDataHisMapper = planInfoDataHisMapper;
        this.planInfoDataHisSearchRepository = planInfoDataHisSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PlanInfoDataHisDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PlanInfoDataHisDTO> findByCriteria(PlanInfoDataHisCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PlanInfoDataHis> specification = createSpecification(criteria);
        return planInfoDataHisMapper.toDto(planInfoDataHisRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PlanInfoDataHisDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PlanInfoDataHisDTO> findByCriteria(PlanInfoDataHisCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PlanInfoDataHis> specification = createSpecification(criteria);
        return planInfoDataHisRepository.findAll(specification, page)
            .map(planInfoDataHisMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PlanInfoDataHisCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PlanInfoDataHis> specification = createSpecification(criteria);
        return planInfoDataHisRepository.count(specification);
    }

    /**
     * Function to convert PlanInfoDataHisCriteria to a {@link Specification}
     */
    private Specification<PlanInfoDataHis> createSpecification(PlanInfoDataHisCriteria criteria) {
        Specification<PlanInfoDataHis> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PlanInfoDataHis_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PlanInfoDataHis_.name));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), PlanInfoDataHis_.sortString));
            }
            if (criteria.getDescString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescString(), PlanInfoDataHis_.descString));
            }
            if (criteria.getJsonString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJsonString(), PlanInfoDataHis_.jsonString));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), PlanInfoDataHis_.remarks));
            }
            if (criteria.getRefEvent() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRefEvent(), PlanInfoDataHis_.refEvent));
            }
            if (criteria.getAttachmentPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentPath(), PlanInfoDataHis_.attachmentPath));
            }
            if (criteria.getAttachmentName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentName(), PlanInfoDataHis_.attachmentName));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), PlanInfoDataHis_.imageBlobName));
            }
            if (criteria.getUsingFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getUsingFlag(), PlanInfoDataHis_.usingFlag));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), PlanInfoDataHis_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), PlanInfoDataHis_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), PlanInfoDataHis_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), PlanInfoDataHis_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), PlanInfoDataHis_.updateTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), PlanInfoDataHis_.verifyTime));
            }
            if (criteria.getVerifyNeed() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifyNeed(), PlanInfoDataHis_.verifyNeed));
            }
            if (criteria.getVerifyOpinion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVerifyOpinion(), PlanInfoDataHis_.verifyOpinion));
            }
            if (criteria.getViewCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getViewCount(), PlanInfoDataHis_.viewCount));
            }
            if (criteria.getViewPermission() != null) {
                specification = specification.and(buildSpecification(criteria.getViewPermission(), PlanInfoDataHis_.viewPermission));
            }
            if (criteria.getViewPermPersion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getViewPermPersion(), PlanInfoDataHis_.viewPermPersion));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), PlanInfoDataHis_.serialNumber));
            }
            if (criteria.getVersionNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVersionNumber(), PlanInfoDataHis_.versionNumber));
            }
            if (criteria.getParaSourceString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParaSourceString(), PlanInfoDataHis_.paraSourceString));
            }
            if (criteria.getFeatureKeyword() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFeatureKeyword(), PlanInfoDataHis_.featureKeyword));
            }
            if (criteria.getSuggestion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSuggestion(), PlanInfoDataHis_.suggestion));
            }
            if (criteria.getReleaseScope() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReleaseScope(), PlanInfoDataHis_.releaseScope));
            }
            if (criteria.getCurrentStepOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrentStepOrder(), PlanInfoDataHis_.currentStepOrder));
            }
            if (criteria.getVerifyRecId() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifyRecId(),
                    root -> root.join(PlanInfoDataHis_.verifyRec, JoinType.LEFT).get(VerifyRec_.id)));
            }
            if (criteria.getParaTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaTypeId(),
                    root -> root.join(PlanInfoDataHis_.paraType, JoinType.LEFT).get(ParaType_.id)));
            }
            if (criteria.getParaClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaClassId(),
                    root -> root.join(PlanInfoDataHis_.paraClass, JoinType.LEFT).get(ParaClass_.id)));
            }
            if (criteria.getParaCatId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaCatId(),
                    root -> root.join(PlanInfoDataHis_.paraCat, JoinType.LEFT).get(ParaCat_.id)));
            }
            if (criteria.getParaStateId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaStateId(),
                    root -> root.join(PlanInfoDataHis_.paraState, JoinType.LEFT).get(ParaState_.id)));
            }
            if (criteria.getParaSourceId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaSourceId(),
                    root -> root.join(PlanInfoDataHis_.paraSource, JoinType.LEFT).get(ParaSource_.id)));
            }
            if (criteria.getParaAttrId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaAttrId(),
                    root -> root.join(PlanInfoDataHis_.paraAttr, JoinType.LEFT).get(ParaAttr_.id)));
            }
            if (criteria.getParaPropId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaPropId(),
                    root -> root.join(PlanInfoDataHis_.paraProp, JoinType.LEFT).get(ParaProp_.id)));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(PlanInfoDataHis_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getCreatedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedDepById(),
                    root -> root.join(PlanInfoDataHis_.createdDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getOwnerById() != null) {
                specification = specification.and(buildSpecification(criteria.getOwnerById(),
                    root -> root.join(PlanInfoDataHis_.ownerBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getOwnerDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getOwnerDepById(),
                    root -> root.join(PlanInfoDataHis_.ownerDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(PlanInfoDataHis_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedDepById(),
                    root -> root.join(PlanInfoDataHis_.modifiedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(PlanInfoDataHis_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedDepById(),
                    root -> root.join(PlanInfoDataHis_.verifiedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getPublishedById() != null) {
                specification = specification.and(buildSpecification(criteria.getPublishedById(),
                    root -> root.join(PlanInfoDataHis_.publishedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getPublishedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getPublishedDepById(),
                    root -> root.join(PlanInfoDataHis_.publishedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(PlanInfoDataHis_.parent, JoinType.LEFT).get(PlanInfoDataHis_.id)));
            }
            if (criteria.getPlanInfoId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoId(),
                    root -> root.join(PlanInfoDataHis_.planInfo, JoinType.LEFT).get(PlanInfo_.id)));
            }
        }
        return specification;
    }
}
