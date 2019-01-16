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

import com.aerothinker.plandb.domain.PlanInfoStepDataAtchHis;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.PlanInfoStepDataAtchHisRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepDataAtchHisSearchRepository;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchHisCriteria;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchHisDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepDataAtchHisMapper;

/**
 * Service for executing complex queries for PlanInfoStepDataAtchHis entities in the database.
 * The main input is a {@link PlanInfoStepDataAtchHisCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PlanInfoStepDataAtchHisDTO} or a {@link Page} of {@link PlanInfoStepDataAtchHisDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PlanInfoStepDataAtchHisQueryService extends QueryService<PlanInfoStepDataAtchHis> {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepDataAtchHisQueryService.class);

    private final PlanInfoStepDataAtchHisRepository planInfoStepDataAtchHisRepository;

    private final PlanInfoStepDataAtchHisMapper planInfoStepDataAtchHisMapper;

    private final PlanInfoStepDataAtchHisSearchRepository planInfoStepDataAtchHisSearchRepository;

    public PlanInfoStepDataAtchHisQueryService(PlanInfoStepDataAtchHisRepository planInfoStepDataAtchHisRepository, PlanInfoStepDataAtchHisMapper planInfoStepDataAtchHisMapper, PlanInfoStepDataAtchHisSearchRepository planInfoStepDataAtchHisSearchRepository) {
        this.planInfoStepDataAtchHisRepository = planInfoStepDataAtchHisRepository;
        this.planInfoStepDataAtchHisMapper = planInfoStepDataAtchHisMapper;
        this.planInfoStepDataAtchHisSearchRepository = planInfoStepDataAtchHisSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PlanInfoStepDataAtchHisDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PlanInfoStepDataAtchHisDTO> findByCriteria(PlanInfoStepDataAtchHisCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PlanInfoStepDataAtchHis> specification = createSpecification(criteria);
        return planInfoStepDataAtchHisMapper.toDto(planInfoStepDataAtchHisRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PlanInfoStepDataAtchHisDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PlanInfoStepDataAtchHisDTO> findByCriteria(PlanInfoStepDataAtchHisCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PlanInfoStepDataAtchHis> specification = createSpecification(criteria);
        return planInfoStepDataAtchHisRepository.findAll(specification, page)
            .map(planInfoStepDataAtchHisMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PlanInfoStepDataAtchHisCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PlanInfoStepDataAtchHis> specification = createSpecification(criteria);
        return planInfoStepDataAtchHisRepository.count(specification);
    }

    /**
     * Function to convert PlanInfoStepDataAtchHisCriteria to a {@link Specification}
     */
    private Specification<PlanInfoStepDataAtchHis> createSpecification(PlanInfoStepDataAtchHisCriteria criteria) {
        Specification<PlanInfoStepDataAtchHis> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PlanInfoStepDataAtchHis_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PlanInfoStepDataAtchHis_.name));
            }
            if (criteria.getStoreDir() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreDir(), PlanInfoStepDataAtchHis_.storeDir));
            }
            if (criteria.getStoreName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreName(), PlanInfoStepDataAtchHis_.storeName));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), PlanInfoStepDataAtchHis_.sortString));
            }
            if (criteria.getFileType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileType(), PlanInfoStepDataAtchHis_.fileType));
            }
            if (criteria.getDeleteFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getDeleteFlag(), PlanInfoStepDataAtchHis_.deleteFlag));
            }
            if (criteria.getStoreType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreType(), PlanInfoStepDataAtchHis_.storeType));
            }
            if (criteria.getVer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVer(), PlanInfoStepDataAtchHis_.ver));
            }
            if (criteria.getEncryptedFlag() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEncryptedFlag(), PlanInfoStepDataAtchHis_.encryptedFlag));
            }
            if (criteria.getEncryptedType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEncryptedType(), PlanInfoStepDataAtchHis_.encryptedType));
            }
            if (criteria.getJsonString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJsonString(), PlanInfoStepDataAtchHis_.jsonString));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), PlanInfoStepDataAtchHis_.remarks));
            }
            if (criteria.getAttachmentPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentPath(), PlanInfoStepDataAtchHis_.attachmentPath));
            }
            if (criteria.getAttachmentName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttachmentName(), PlanInfoStepDataAtchHis_.attachmentName));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), PlanInfoStepDataAtchHis_.imageBlobName));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), PlanInfoStepDataAtchHis_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), PlanInfoStepDataAtchHis_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), PlanInfoStepDataAtchHis_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), PlanInfoStepDataAtchHis_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), PlanInfoStepDataAtchHis_.updateTime));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(PlanInfoStepDataAtchHis_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(PlanInfoStepDataAtchHis_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getPlanInfoStepAtchId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoStepAtchId(),
                    root -> root.join(PlanInfoStepDataAtchHis_.planInfoStepAtch, JoinType.LEFT).get(PlanInfoStepAtch_.id)));
            }
            if (criteria.getPlanInfoStepDataHisId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanInfoStepDataHisId(),
                    root -> root.join(PlanInfoStepDataAtchHis_.planInfoStepDataHis, JoinType.LEFT).get(PlanInfoStepDataHis_.id)));
            }
        }
        return specification;
    }
}
