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

import com.aerothinker.plandb.domain.PlanInfo;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.PlanInfoRepository;
import com.aerothinker.plandb.repository.search.PlanInfoSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoCriteria;
import com.aerothinker.plandb.service.dto.PlanInfoDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoMapper;

/**
 * Service for executing complex queries for PlanInfo entities in the database.
 * The main input is a {@link PlanInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PlanInfoDTO} or a {@link Page} of {@link PlanInfoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PlanInfoQueryService extends QueryService<PlanInfo> {

    private final Logger log = LoggerFactory.getLogger(PlanInfoQueryService.class);

    private final PlanInfoRepository planInfoRepository;

    private final PlanInfoMapper planInfoMapper;

    private final PlanInfoSearchRepository planInfoSearchRepository;

    public PlanInfoQueryService(PlanInfoRepository planInfoRepository, PlanInfoMapper planInfoMapper, PlanInfoSearchRepository planInfoSearchRepository) {
        this.planInfoRepository = planInfoRepository;
        this.planInfoMapper = planInfoMapper;
        this.planInfoSearchRepository = planInfoSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PlanInfoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PlanInfoDTO> findByCriteria(PlanInfoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PlanInfo> specification = createSpecification(criteria);
        return planInfoMapper.toDto(planInfoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PlanInfoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PlanInfoDTO> findByCriteria(PlanInfoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PlanInfo> specification = createSpecification(criteria);
        return planInfoRepository.findAll(specification, page)
            .map(planInfoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PlanInfoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PlanInfo> specification = createSpecification(criteria);
        return planInfoRepository.count(specification);
    }

    /**
     * Function to convert PlanInfoCriteria to a {@link Specification}
     */
    private Specification<PlanInfo> createSpecification(PlanInfoCriteria criteria) {
        Specification<PlanInfo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PlanInfo_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PlanInfo_.name));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), PlanInfo_.sortString));
            }
            if (criteria.getDescString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescString(), PlanInfo_.descString));
            }
            if (criteria.getJsonString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJsonString(), PlanInfo_.jsonString));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), PlanInfo_.remarks));
            }
            if (criteria.getRefEvent() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRefEvent(), PlanInfo_.refEvent));
            }
            if (criteria.getAttachmentPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentPath(), PlanInfo_.attachmentPath));
            }
            if (criteria.getAttachmentName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentName(), PlanInfo_.attachmentName));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), PlanInfo_.imageBlobName));
            }
            if (criteria.getUsingFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getUsingFlag(), PlanInfo_.usingFlag));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), PlanInfo_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), PlanInfo_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), PlanInfo_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), PlanInfo_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), PlanInfo_.updateTime));
            }
            if (criteria.getPublishedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPublishedTime(), PlanInfo_.publishedTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), PlanInfo_.verifyTime));
            }
            if (criteria.getVerifyNeed() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifyNeed(), PlanInfo_.verifyNeed));
            }
            if (criteria.getVerifyOpinion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVerifyOpinion(), PlanInfo_.verifyOpinion));
            }
            if (criteria.getViewCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getViewCount(), PlanInfo_.viewCount));
            }
            if (criteria.getViewPermission() != null) {
                specification = specification.and(buildSpecification(criteria.getViewPermission(), PlanInfo_.viewPermission));
            }
            if (criteria.getViewPermPersion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getViewPermPersion(), PlanInfo_.viewPermPersion));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), PlanInfo_.serialNumber));
            }
            if (criteria.getVersionNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVersionNumber(), PlanInfo_.versionNumber));
            }
            if (criteria.getParaSourceString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParaSourceString(), PlanInfo_.paraSourceString));
            }
            if (criteria.getFeatureKeyword() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFeatureKeyword(), PlanInfo_.featureKeyword));
            }
            if (criteria.getSuggestion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSuggestion(), PlanInfo_.suggestion));
            }
            if (criteria.getReleaseScope() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReleaseScope(), PlanInfo_.releaseScope));
            }
            if (criteria.getVerifyRecId() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifyRecId(),
                    root -> root.join(PlanInfo_.verifyRec, JoinType.LEFT).get(VerifyRec_.id)));
            }
            if (criteria.getParaTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaTypeId(),
                    root -> root.join(PlanInfo_.paraType, JoinType.LEFT).get(ParaType_.id)));
            }
            if (criteria.getParaClassId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaClassId(),
                    root -> root.join(PlanInfo_.paraClass, JoinType.LEFT).get(ParaClass_.id)));
            }
            if (criteria.getParaCatId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaCatId(),
                    root -> root.join(PlanInfo_.paraCat, JoinType.LEFT).get(ParaCat_.id)));
            }
            if (criteria.getParaStateId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaStateId(),
                    root -> root.join(PlanInfo_.paraState, JoinType.LEFT).get(ParaState_.id)));
            }
            if (criteria.getParaSourceId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaSourceId(),
                    root -> root.join(PlanInfo_.paraSource, JoinType.LEFT).get(ParaSource_.id)));
            }
            if (criteria.getParaAttrId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaAttrId(),
                    root -> root.join(PlanInfo_.paraAttr, JoinType.LEFT).get(ParaAttr_.id)));
            }
            if (criteria.getParaPropId() != null) {
                specification = specification.and(buildSpecification(criteria.getParaPropId(),
                    root -> root.join(PlanInfo_.paraProp, JoinType.LEFT).get(ParaProp_.id)));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(PlanInfo_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getCreatedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedDepById(),
                    root -> root.join(PlanInfo_.createdDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getOwnerById() != null) {
                specification = specification.and(buildSpecification(criteria.getOwnerById(),
                    root -> root.join(PlanInfo_.ownerBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getOwnerDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getOwnerDepById(),
                    root -> root.join(PlanInfo_.ownerDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(PlanInfo_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedDepById(),
                    root -> root.join(PlanInfo_.modifiedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(PlanInfo_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedDepById(),
                    root -> root.join(PlanInfo_.verifiedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getPublishedById() != null) {
                specification = specification.and(buildSpecification(criteria.getPublishedById(),
                    root -> root.join(PlanInfo_.publishedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getPublishedDepById() != null) {
                specification = specification.and(buildSpecification(criteria.getPublishedDepById(),
                    root -> root.join(PlanInfo_.publishedDepBy, JoinType.LEFT).get(RmsDep_.id)));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(PlanInfo_.parent, JoinType.LEFT).get(PlanInfo_.id)));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildSpecification(criteria.getCompanyId(),
                    root -> root.join(PlanInfo_.company, JoinType.LEFT).get(Company_.id)));
            }
        }
        return specification;
    }
}
