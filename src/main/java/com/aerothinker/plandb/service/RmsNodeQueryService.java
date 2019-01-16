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

import com.aerothinker.plandb.domain.RmsNode;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.RmsNodeRepository;
import com.aerothinker.plandb.repository.search.RmsNodeSearchRepository;
import com.aerothinker.plandb.service.dto.RmsNodeCriteria;
import com.aerothinker.plandb.service.dto.RmsNodeDTO;
import com.aerothinker.plandb.service.mapper.RmsNodeMapper;

/**
 * Service for executing complex queries for RmsNode entities in the database.
 * The main input is a {@link RmsNodeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RmsNodeDTO} or a {@link Page} of {@link RmsNodeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RmsNodeQueryService extends QueryService<RmsNode> {

    private final Logger log = LoggerFactory.getLogger(RmsNodeQueryService.class);

    private final RmsNodeRepository rmsNodeRepository;

    private final RmsNodeMapper rmsNodeMapper;

    private final RmsNodeSearchRepository rmsNodeSearchRepository;

    public RmsNodeQueryService(RmsNodeRepository rmsNodeRepository, RmsNodeMapper rmsNodeMapper, RmsNodeSearchRepository rmsNodeSearchRepository) {
        this.rmsNodeRepository = rmsNodeRepository;
        this.rmsNodeMapper = rmsNodeMapper;
        this.rmsNodeSearchRepository = rmsNodeSearchRepository;
    }

    /**
     * Return a {@link List} of {@link RmsNodeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RmsNodeDTO> findByCriteria(RmsNodeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RmsNode> specification = createSpecification(criteria);
        return rmsNodeMapper.toDto(rmsNodeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RmsNodeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RmsNodeDTO> findByCriteria(RmsNodeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RmsNode> specification = createSpecification(criteria);
        return rmsNodeRepository.findAll(specification, page)
            .map(rmsNodeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RmsNodeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RmsNode> specification = createSpecification(criteria);
        return rmsNodeRepository.count(specification);
    }

    /**
     * Function to convert RmsNodeCriteria to a {@link Specification}
     */
    private Specification<RmsNode> createSpecification(RmsNodeCriteria criteria) {
        Specification<RmsNode> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RmsNode_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), RmsNode_.name));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), RmsNode_.serialNumber));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), RmsNode_.sortString));
            }
            if (criteria.getDescString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescString(), RmsNode_.descString));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), RmsNode_.imageBlobName));
            }
            if (criteria.getUsingFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getUsingFlag(), RmsNode_.usingFlag));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), RmsNode_.remarks));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), RmsNode_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), RmsNode_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), RmsNode_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), RmsNode_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), RmsNode_.updateTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), RmsNode_.verifyTime));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(RmsNode_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(RmsNode_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(RmsNode_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(RmsNode_.parent, JoinType.LEFT).get(RmsNode_.id)));
            }
            if (criteria.getRmsRoleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRmsRoleId(),
                    root -> root.join(RmsNode_.rmsRoles, JoinType.LEFT).get(RmsRole_.id)));
            }
        }
        return specification;
    }
}
