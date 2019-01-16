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

import com.aerothinker.plandb.domain.ParaCat;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.ParaCatRepository;
import com.aerothinker.plandb.repository.search.ParaCatSearchRepository;
import com.aerothinker.plandb.service.dto.ParaCatCriteria;
import com.aerothinker.plandb.service.dto.ParaCatDTO;
import com.aerothinker.plandb.service.mapper.ParaCatMapper;

/**
 * Service for executing complex queries for ParaCat entities in the database.
 * The main input is a {@link ParaCatCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ParaCatDTO} or a {@link Page} of {@link ParaCatDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ParaCatQueryService extends QueryService<ParaCat> {

    private final Logger log = LoggerFactory.getLogger(ParaCatQueryService.class);

    private final ParaCatRepository paraCatRepository;

    private final ParaCatMapper paraCatMapper;

    private final ParaCatSearchRepository paraCatSearchRepository;

    public ParaCatQueryService(ParaCatRepository paraCatRepository, ParaCatMapper paraCatMapper, ParaCatSearchRepository paraCatSearchRepository) {
        this.paraCatRepository = paraCatRepository;
        this.paraCatMapper = paraCatMapper;
        this.paraCatSearchRepository = paraCatSearchRepository;
    }

    /**
     * Return a {@link List} of {@link ParaCatDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ParaCatDTO> findByCriteria(ParaCatCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ParaCat> specification = createSpecification(criteria);
        return paraCatMapper.toDto(paraCatRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ParaCatDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ParaCatDTO> findByCriteria(ParaCatCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ParaCat> specification = createSpecification(criteria);
        return paraCatRepository.findAll(specification, page)
            .map(paraCatMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ParaCatCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ParaCat> specification = createSpecification(criteria);
        return paraCatRepository.count(specification);
    }

    /**
     * Function to convert ParaCatCriteria to a {@link Specification}
     */
    private Specification<ParaCat> createSpecification(ParaCatCriteria criteria) {
        Specification<ParaCat> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ParaCat_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ParaCat_.name));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), ParaCat_.serialNumber));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), ParaCat_.sortString));
            }
            if (criteria.getDescString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescString(), ParaCat_.descString));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), ParaCat_.imageBlobName));
            }
            if (criteria.getUsingFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getUsingFlag(), ParaCat_.usingFlag));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), ParaCat_.remarks));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), ParaCat_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), ParaCat_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), ParaCat_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), ParaCat_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), ParaCat_.updateTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), ParaCat_.verifyTime));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(ParaCat_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(ParaCat_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(ParaCat_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(ParaCat_.parent, JoinType.LEFT).get(ParaCat_.id)));
            }
        }
        return specification;
    }
}
