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

import com.aerothinker.plandb.domain.ParaAttr;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.ParaAttrRepository;
import com.aerothinker.plandb.repository.search.ParaAttrSearchRepository;
import com.aerothinker.plandb.service.dto.ParaAttrCriteria;
import com.aerothinker.plandb.service.dto.ParaAttrDTO;
import com.aerothinker.plandb.service.mapper.ParaAttrMapper;

/**
 * Service for executing complex queries for ParaAttr entities in the database.
 * The main input is a {@link ParaAttrCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ParaAttrDTO} or a {@link Page} of {@link ParaAttrDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ParaAttrQueryService extends QueryService<ParaAttr> {

    private final Logger log = LoggerFactory.getLogger(ParaAttrQueryService.class);

    private final ParaAttrRepository paraAttrRepository;

    private final ParaAttrMapper paraAttrMapper;

    private final ParaAttrSearchRepository paraAttrSearchRepository;

    public ParaAttrQueryService(ParaAttrRepository paraAttrRepository, ParaAttrMapper paraAttrMapper, ParaAttrSearchRepository paraAttrSearchRepository) {
        this.paraAttrRepository = paraAttrRepository;
        this.paraAttrMapper = paraAttrMapper;
        this.paraAttrSearchRepository = paraAttrSearchRepository;
    }

    /**
     * Return a {@link List} of {@link ParaAttrDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ParaAttrDTO> findByCriteria(ParaAttrCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ParaAttr> specification = createSpecification(criteria);
        return paraAttrMapper.toDto(paraAttrRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ParaAttrDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ParaAttrDTO> findByCriteria(ParaAttrCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ParaAttr> specification = createSpecification(criteria);
        return paraAttrRepository.findAll(specification, page)
            .map(paraAttrMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ParaAttrCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ParaAttr> specification = createSpecification(criteria);
        return paraAttrRepository.count(specification);
    }

    /**
     * Function to convert ParaAttrCriteria to a {@link Specification}
     */
    private Specification<ParaAttr> createSpecification(ParaAttrCriteria criteria) {
        Specification<ParaAttr> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ParaAttr_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ParaAttr_.name));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), ParaAttr_.serialNumber));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), ParaAttr_.sortString));
            }
            if (criteria.getDescString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescString(), ParaAttr_.descString));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), ParaAttr_.imageBlobName));
            }
            if (criteria.getUsingFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getUsingFlag(), ParaAttr_.usingFlag));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), ParaAttr_.remarks));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), ParaAttr_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), ParaAttr_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), ParaAttr_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), ParaAttr_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), ParaAttr_.updateTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), ParaAttr_.verifyTime));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(ParaAttr_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(ParaAttr_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(ParaAttr_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(ParaAttr_.parent, JoinType.LEFT).get(ParaAttr_.id)));
            }
        }
        return specification;
    }
}
