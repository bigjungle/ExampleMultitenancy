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

import com.aerothinker.plandb.domain.PlanInfoStepDataAtch;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.PlanInfoStepDataAtchRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepDataAtchSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchCriteria;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepDataAtchMapper;

/**
 * Service for executing complex queries for PlanInfoStepDataAtch entities in the database.
 * The main input is a {@link PlanInfoStepDataAtchCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PlanInfoStepDataAtchDTO} or a {@link Page} of {@link PlanInfoStepDataAtchDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PlanInfoStepDataAtchQueryService extends QueryService<PlanInfoStepDataAtch> {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepDataAtchQueryService.class);

    private final PlanInfoStepDataAtchRepository planInfoStepDataAtchRepository;

    private final PlanInfoStepDataAtchMapper planInfoStepDataAtchMapper;

    private final PlanInfoStepDataAtchSearchRepository planInfoStepDataAtchSearchRepository;

    public PlanInfoStepDataAtchQueryService(PlanInfoStepDataAtchRepository planInfoStepDataAtchRepository, PlanInfoStepDataAtchMapper planInfoStepDataAtchMapper, PlanInfoStepDataAtchSearchRepository planInfoStepDataAtchSearchRepository) {
        this.planInfoStepDataAtchRepository = planInfoStepDataAtchRepository;
        this.planInfoStepDataAtchMapper = planInfoStepDataAtchMapper;
        this.planInfoStepDataAtchSearchRepository = planInfoStepDataAtchSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PlanInfoStepDataAtchDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PlanInfoStepDataAtchDTO> findByCriteria(PlanInfoStepDataAtchCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PlanInfoStepDataAtch> specification = createSpecification(criteria);
        return planInfoStepDataAtchMapper.toDto(planInfoStepDataAtchRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PlanInfoStepDataAtchDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDataAtchDTO> findByCriteria(PlanInfoStepDataAtchCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PlanInfoStepDataAtch> specification = createSpecification(criteria);
        return planInfoStepDataAtchRepository.findAll(specification, page)
            .map(planInfoStepDataAtchMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PlanInfoStepDataAtchCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PlanInfoStepDataAtch> specification = createSpecification(criteria);
        return planInfoStepDataAtchRepository.count(specification);
    }

    /**
     * Function to convert PlanInfoStepDataAtchCriteria to a {@link Specification}
     */
    private Specification<PlanInfoStepDataAtch> createSpecification(PlanInfoStepDataAtchCriteria criteria) {
        Specification<PlanInfoStepDataAtch> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PlanInfoStepDataAtch_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PlanInfoStepDataAtch_.name));
            }
            if (criteria.getStoreDir() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreDir(), PlanInfoStepDataAtch_.storeDir));
            }
            if (criteria.getStoreName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreName(), PlanInfoStepDataAtch_.storeName));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), PlanInfoStepDataAtch_.sortString));
            }
            if (criteria.getFileType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileType(), PlanInfoStepDataAtch_.fileType));
            }
            if (criteria.getDeleteFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getDeleteFlag(), PlanInfoStepDataAtch_.deleteFlag));
            }
            if (criteria.getStoreType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreType(), PlanInfoStepDataAtch_.storeType));
            }
            if (criteria.getVer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVer(), PlanInfoStepDataAtch_.ver));
            }
            if (criteria.getEncryptedFlag() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEncryptedFlag(), PlanInfoStepDataAtch_.encryptedFlag));
            }
            if (criteria.getEncryptedType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEncryptedType(), PlanInfoStepDataAtch_.encryptedType));
            }
            if (criteria.getJsonString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJsonString(), PlanInfoStepDataAtch_.jsonString));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), PlanInfoStepDataAtch_.remarks));
            }
            if (criteria.getAttachmentPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentPath(), PlanInfoStepDataAtch_.attachmentPath));
            }
            if (criteria.getAttachmentName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentName(), PlanInfoStepDataAtch_.attachmentName));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), PlanInfoStepDataAtch_.imageBlobName));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), PlanInfoStepDataAtch_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), PlanInfoStepDataAtch_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), PlanInfoStepDataAtch_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), PlanInfoStepDataAtch_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), PlanInfoStepDataAtch_.updateTime));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(PlanInfoStepDataAtch_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(PlanInfoStepDataAtch_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getPlanInfoStepAtchId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoStepAtchId(),
                    root -> root.join(PlanInfoStepDataAtch_.planInfoStepAtch, JoinType.LEFT).get(PlanInfoStepAtch_.id)));
            }
            if (criteria.getPlanInfoStepDataId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoStepDataId(),
                    root -> root.join(PlanInfoStepDataAtch_.planInfoStepData, JoinType.LEFT).get(PlanInfoStepData_.id)));
            }
        }
        return specification;
    }
}
