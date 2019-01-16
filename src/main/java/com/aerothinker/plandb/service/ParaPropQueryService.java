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

import com.aerothinker.plandb.domain.ParaProp;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.ParaPropRepository;
import com.aerothinker.plandb.repository.search.ParaPropSearchRepository;
import com.aerothinker.plandb.service.dto.ParaPropCriteria;
import com.aerothinker.plandb.service.dto.ParaPropDTO;
import com.aerothinker.plandb.service.mapper.ParaPropMapper;

/**
 * Service for executing complex queries for ParaProp entities in the database.
 * The main input is a {@link ParaPropCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ParaPropDTO} or a {@link Page} of {@link ParaPropDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ParaPropQueryService extends QueryService<ParaProp> {

    private final Logger log = LoggerFactory.getLogger(ParaPropQueryService.class);

    private final ParaPropRepository paraPropRepository;

    private final ParaPropMapper paraPropMapper;

    private final ParaPropSearchRepository paraPropSearchRepository;

    public ParaPropQueryService(ParaPropRepository paraPropRepository, ParaPropMapper paraPropMapper, ParaPropSearchRepository paraPropSearchRepository) {
        this.paraPropRepository = paraPropRepository;
        this.paraPropMapper = paraPropMapper;
        this.paraPropSearchRepository = paraPropSearchRepository;
    }

    /**
     * Return a {@link List} of {@link ParaPropDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ParaPropDTO> findByCriteria(ParaPropCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ParaProp> specification = createSpecification(criteria);
        return paraPropMapper.toDto(paraPropRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ParaPropDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ParaPropDTO> findByCriteria(ParaPropCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ParaProp> specification = createSpecification(criteria);
        return paraPropRepository.findAll(specification, page)
            .map(paraPropMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ParaPropCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ParaProp> specification = createSpecification(criteria);
        return paraPropRepository.count(specification);
    }

    /**
     * Function to convert ParaPropCriteria to a {@link Specification}
     */
    private Specification<ParaProp> createSpecification(ParaPropCriteria criteria) {
        Specification<ParaProp> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ParaProp_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ParaProp_.name));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), ParaProp_.serialNumber));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), ParaProp_.sortString));
            }
            if (criteria.getDescString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescString(), ParaProp_.descString));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), ParaProp_.imageBlobName));
            }
            if (criteria.getUsingFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getUsingFlag(), ParaProp_.usingFlag));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), ParaProp_.remarks));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), ParaProp_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), ParaProp_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), ParaProp_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), ParaProp_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), ParaProp_.updateTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), ParaProp_.verifyTime));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(ParaProp_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(ParaProp_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(ParaProp_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(ParaProp_.parent, JoinType.LEFT).get(ParaProp_.id)));
            }
        }
        return specification;
    }
}
