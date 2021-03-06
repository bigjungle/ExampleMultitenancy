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

import com.aerothinker.plandb.domain.ParaType;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.ParaTypeRepository;
import com.aerothinker.plandb.repository.search.ParaTypeSearchRepository;
import com.aerothinker.plandb.service.dto.ParaTypeCriteria;
import com.aerothinker.plandb.service.dto.ParaTypeDTO;
import com.aerothinker.plandb.service.mapper.ParaTypeMapper;

/**
 * Service for executing complex queries for ParaType entities in the database.
 * The main input is a {@link ParaTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ParaTypeDTO} or a {@link Page} of {@link ParaTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ParaTypeQueryService extends QueryService<ParaType> {

    private final Logger log = LoggerFactory.getLogger(ParaTypeQueryService.class);

    private final ParaTypeRepository paraTypeRepository;

    private final ParaTypeMapper paraTypeMapper;

    private final ParaTypeSearchRepository paraTypeSearchRepository;

    public ParaTypeQueryService(ParaTypeRepository paraTypeRepository, ParaTypeMapper paraTypeMapper, ParaTypeSearchRepository paraTypeSearchRepository) {
        this.paraTypeRepository = paraTypeRepository;
        this.paraTypeMapper = paraTypeMapper;
        this.paraTypeSearchRepository = paraTypeSearchRepository;
    }

    /**
     * Return a {@link List} of {@link ParaTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ParaTypeDTO> findByCriteria(ParaTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ParaType> specification = createSpecification(criteria);
        return paraTypeMapper.toDto(paraTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ParaTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ParaTypeDTO> findByCriteria(ParaTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ParaType> specification = createSpecification(criteria);
        return paraTypeRepository.findAll(specification, page)
            .map(paraTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ParaTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ParaType> specification = createSpecification(criteria);
        return paraTypeRepository.count(specification);
    }

    /**
     * Function to convert ParaTypeCriteria to a {@link Specification}
     */
    private Specification<ParaType> createSpecification(ParaTypeCriteria criteria) {
        Specification<ParaType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ParaType_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ParaType_.name));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), ParaType_.serialNumber));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), ParaType_.sortString));
            }
            if (criteria.getDescString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescString(), ParaType_.descString));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), ParaType_.imageBlobName));
            }
            if (criteria.getUsingFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getUsingFlag(), ParaType_.usingFlag));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), ParaType_.remarks));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), ParaType_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), ParaType_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), ParaType_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), ParaType_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), ParaType_.updateTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), ParaType_.verifyTime));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(ParaType_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(ParaType_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(ParaType_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(ParaType_.parent, JoinType.LEFT).get(ParaType_.id)));
            }
        }
        return specification;
    }
}
