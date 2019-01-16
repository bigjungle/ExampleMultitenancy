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

import com.aerothinker.plandb.domain.PlanInfoData;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.PlanInfoDataRepository;
import com.aerothinker.plandb.repository.search.PlanInfoDataSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoDataCriteria;
import com.aerothinker.plandb.service.dto.PlanInfoDataDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoDataMapper;

/**
 * Service for executing complex queries for PlanInfoData entities in the database.
 * The main input is a {@link PlanInfoDataCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PlanInfoDataDTO} or a {@link Page} of {@link PlanInfoDataDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PlanInfoDataQueryService extends QueryService<PlanInfoData> {

    private final Logger log = LoggerFactory.getLogger(PlanInfoDataQueryService.class);

    private final PlanInfoDataRepository planInfoDataRepository;

    private final PlanInfoDataMapper planInfoDataMapper;

    private final PlanInfoDataSearchRepository planInfoDataSearchRepository;

    public PlanInfoDataQueryService(PlanInfoDataRepository planInfoDataRepository, PlanInfoDataMapper planInfoDataMapper, PlanInfoDataSearchRepository planInfoDataSearchRepository) {
        this.planInfoDataRepository = planInfoDataRepository;
        this.planInfoDataMapper = planInfoDataMapper;
        this.planInfoDataSearchRepository = planInfoDataSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PlanInfoDataDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PlanInfoDataDTO> findByCriteria(PlanInfoDataCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PlanInfoData> specification = createSpecification(criteria);
        return planInfoDataMapper.toDto(planInfoDataRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PlanInfoDataDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PlanInfoDataDTO> findByCriteria(PlanInfoDataCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PlanInfoData> specification = createSpecification(criteria);
        return planInfoDataRepository.findAll(specification, page)
            .map(planInfoDataMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PlanInfoDataCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PlanInfoData> specification = createSpecification(criteria);
        return planInfoDataRepository.count(specification);
    }

    /**
     * Function to convert PlanInfoDataCriteria to a {@link Specification}
     */
    private Specification<PlanInfoData> createSpecification(PlanInfoDataCriteria criteria) {
        Specification<PlanInfoData> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PlanInfoData_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PlanInfoData_.name));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), PlanInfoData_.sortString));
            }
            if (criteria.getDescString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescString(), PlanInfoData_.descString));
            }
            if (criteria.getJsonString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJsonString(), PlanInfoData_.jsonString));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), PlanInfoData_.remarks));
            }
            if (criteria.getRefEvent() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRefEvent(), PlanInfoData_.refEvent));
            }
            if (criteria.getAttachmentPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentPath(), PlanInfoData_.attachmentPath));
            }
            if (criteria.getAttachmentName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentName(), PlanInfoData_.attachmentName));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), PlanInfoData_.imageBlobName));
            }
            if (criteria.getUsingFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getUsingFlag(), PlanInfoData_.usingFlag));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), PlanInfoData_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), PlanInfoData_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), PlanInfoData_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), PlanInfoData_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), PlanInfoData_.updateTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), PlanInfoData_.verifyTime));
            }
            if (criteria.getVerifyNeed() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifyNeed(), PlanInfoData_.verifyNeed));
            }
            if (criteria.getVerifyOpinion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVerifyOpinion(), PlanInfoData_.verifyOpinion));
            }
            if (criteria.getViewCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getViewCount(), PlanInfoData_.viewCount));
            }
            if (criteria.getViewPermission() != null) {
                specification = specification.and(buildSpecification(criteria.getViewPermission(), PlanInfoData_.viewPermission));
            }
            if (criteria.getViewPermPersion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getViewPermPersion(), PlanInfoData_.viewPermPersion));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), PlanInfoData_.serialNumber));
            }
            if (criteria.getVersionNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVersionNumber(), PlanInfoData_.versionNumber));
            }
            if (criteria.getParaSourceString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParaSourceString(), PlanInfoData_.paraSourceString));
            }
            if (criteria.getFeatureKeyword() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFeatureKeyword(), PlanInfoData_.featureKeyword));
            }
            if (criteria.getSuggestion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSuggestion(), PlanInfoData_.suggestion));
            }
            if (criteria.getReleaseScope() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReleaseScope(), PlanInfoData_.releaseScope));
            }
            if (criteria.getCurrentStepOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrentStepOrder(), PlanInfoData_.currentStepOrder));
            }
            if (criteria.getVerifyRecId() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifyRecId(),
                    root -> root.join(PlanInfoData_.verifyRec, JoinType.LEFT).get(VerifyRec_.id)));
            }
            if (criteria.getParaTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaTypeId(),
                    root -> root.join(PlanInfoData_.paraType, JoinType.LEFT).get(ParaType_.id)));
            }
            if (criteria.getParaClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaClassId(),
                    root -> root.join(PlanInfoData_.paraClass, JoinType.LEFT).get(ParaClass_.id)));
            }
            if (criteria.getParaCatId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaCatId(),
                    root -> root.join(PlanInfoData_.paraCat, JoinType.LEFT).get(ParaCat_.id)));
            }
            if (criteria.getParaStateId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaStateId(),
                    root -> root.join(PlanInfoData_.paraState, JoinType.LEFT).get(ParaState_.id)));
            }
            if (criteria.getParaSourceId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaSourceId(),
                    root -> root.join(PlanInfoData_.paraSource, JoinType.LEFT).get(ParaSource_.id)));
            }
            if (criteria.getParaAttrId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaAttrId(),
                    root -> root.join(PlanInfoData_.paraAttr, JoinType.LEFT).get(ParaAttr_.id)));
            }
            if (criteria.getParaPropId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaPropId(),
                    root -> root.join(PlanInfoData_.paraProp, JoinType.LEFT).get(ParaProp_.id)));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(PlanInfoData_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getCreatedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedDepById(),
                    root -> root.join(PlanInfoData_.createdDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getOwnerById() != null) {
                specification = specification.and(buildSpecification(criteria.getOwnerById(),
                    root -> root.join(PlanInfoData_.ownerBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getOwnerDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getOwnerDepById(),
                    root -> root.join(PlanInfoData_.ownerDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(PlanInfoData_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedDepById(),
                    root -> root.join(PlanInfoData_.modifiedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(PlanInfoData_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedDepById(),
                    root -> root.join(PlanInfoData_.verifiedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getPublishedById() != null) {
                specification = specification.and(buildSpecification(criteria.getPublishedById(),
                    root -> root.join(PlanInfoData_.publishedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getPublishedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getPublishedDepById(),
                    root -> root.join(PlanInfoData_.publishedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(PlanInfoData_.parent, JoinType.LEFT).get(PlanInfoData_.id)));
            }
            if (criteria.getPlanInfoId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoId(),
                    root -> root.join(PlanInfoData_.planInfo, JoinType.LEFT).get(PlanInfo_.id)));
            }
        }
        return specification;
    }
}
