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

import com.aerothinker.plandb.domain.PlanInfoStepAtch;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.PlanInfoStepAtchRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepAtchSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoStepAtchCriteria;
import com.aerothinker.plandb.service.dto.PlanInfoStepAtchDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepAtchMapper;

/**
 * Service for executing complex queries for PlanInfoStepAtch entities in the database.
 * The main input is a {@link PlanInfoStepAtchCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PlanInfoStepAtchDTO} or a {@link Page} of {@link PlanInfoStepAtchDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PlanInfoStepAtchQueryService extends QueryService<PlanInfoStepAtch> {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepAtchQueryService.class);

    private final PlanInfoStepAtchRepository planInfoStepAtchRepository;

    private final PlanInfoStepAtchMapper planInfoStepAtchMapper;

    private final PlanInfoStepAtchSearchRepository planInfoStepAtchSearchRepository;

    public PlanInfoStepAtchQueryService(PlanInfoStepAtchRepository planInfoStepAtchRepository, PlanInfoStepAtchMapper planInfoStepAtchMapper, PlanInfoStepAtchSearchRepository planInfoStepAtchSearchRepository) {
        this.planInfoStepAtchRepository = planInfoStepAtchRepository;
        this.planInfoStepAtchMapper = planInfoStepAtchMapper;
        this.planInfoStepAtchSearchRepository = planInfoStepAtchSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PlanInfoStepAtchDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PlanInfoStepAtchDTO> findByCriteria(PlanInfoStepAtchCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PlanInfoStepAtch> specification = createSpecification(criteria);
        return planInfoStepAtchMapper.toDto(planInfoStepAtchRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PlanInfoStepAtchDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PlanInfoStepAtchDTO> findByCriteria(PlanInfoStepAtchCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PlanInfoStepAtch> specification = createSpecification(criteria);
        return planInfoStepAtchRepository.findAll(specification, page)
            .map(planInfoStepAtchMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PlanInfoStepAtchCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PlanInfoStepAtch> specification = createSpecification(criteria);
        return planInfoStepAtchRepository.count(specification);
    }

    /**
     * Function to convert PlanInfoStepAtchCriteria to a {@link Specification}
     */
    private Specification<PlanInfoStepAtch> createSpecification(PlanInfoStepAtchCriteria criteria) {
        Specification<PlanInfoStepAtch> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PlanInfoStepAtch_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PlanInfoStepAtch_.name));
            }
            if (criteria.getStoreDir() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreDir(), PlanInfoStepAtch_.storeDir));
            }
            if (criteria.getStoreName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreName(), PlanInfoStepAtch_.storeName));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), PlanInfoStepAtch_.sortString));
            }
            if (criteria.getFileType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileType(), PlanInfoStepAtch_.fileType));
            }
            if (criteria.getDeleteFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getDeleteFlag(), PlanInfoStepAtch_.deleteFlag));
            }
            if (criteria.getStoreType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreType(), PlanInfoStepAtch_.storeType));
            }
            if (criteria.getVer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVer(), PlanInfoStepAtch_.ver));
            }
            if (criteria.getEncryptedFlag() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEncryptedFlag(), PlanInfoStepAtch_.encryptedFlag));
            }
            if (criteria.getEncryptedType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEncryptedType(), PlanInfoStepAtch_.encryptedType));
            }
            if (criteria.getJsonString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJsonString(), PlanInfoStepAtch_.jsonString));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), PlanInfoStepAtch_.remarks));
            }
            if (criteria.getAttachmentPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentPath(), PlanInfoStepAtch_.attachmentPath));
            }
            if (criteria.getAttachmentName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentName(), PlanInfoStepAtch_.attachmentName));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), PlanInfoStepAtch_.imageBlobName));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), PlanInfoStepAtch_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), PlanInfoStepAtch_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), PlanInfoStepAtch_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), PlanInfoStepAtch_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), PlanInfoStepAtch_.updateTime));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(PlanInfoStepAtch_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(PlanInfoStepAtch_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getPlanInfoStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoStepId(),
                    root -> root.join(PlanInfoStepAtch_.planInfoStep, JoinType.LEFT).get(PlanInfoStep_.id)));
            }
        }
        return specification;
    }
}
