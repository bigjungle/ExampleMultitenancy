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

import com.aerothinker.plandb.domain.RmsRole;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.RmsRoleRepository;
import com.aerothinker.plandb.repository.search.RmsRoleSearchRepository;
import com.aerothinker.plandb.service.dto.RmsRoleCriteria;
import com.aerothinker.plandb.service.dto.RmsRoleDTO;
import com.aerothinker.plandb.service.mapper.RmsRoleMapper;

/**
 * Service for executing complex queries for RmsRole entities in the database.
 * The main input is a {@link RmsRoleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RmsRoleDTO} or a {@link Page} of {@link RmsRoleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RmsRoleQueryService extends QueryService<RmsRole> {

    private final Logger log = LoggerFactory.getLogger(RmsRoleQueryService.class);

    private final RmsRoleRepository rmsRoleRepository;

    private final RmsRoleMapper rmsRoleMapper;

    private final RmsRoleSearchRepository rmsRoleSearchRepository;

    public RmsRoleQueryService(RmsRoleRepository rmsRoleRepository, RmsRoleMapper rmsRoleMapper, RmsRoleSearchRepository rmsRoleSearchRepository) {
        this.rmsRoleRepository = rmsRoleRepository;
        this.rmsRoleMapper = rmsRoleMapper;
        this.rmsRoleSearchRepository = rmsRoleSearchRepository;
    }

    /**
     * Return a {@link List} of {@link RmsRoleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RmsRoleDTO> findByCriteria(RmsRoleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RmsRole> specification = createSpecification(criteria);
        return rmsRoleMapper.toDto(rmsRoleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RmsRoleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RmsRoleDTO> findByCriteria(RmsRoleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RmsRole> specification = createSpecification(criteria);
        return rmsRoleRepository.findAll(specification, page)
            .map(rmsRoleMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RmsRoleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RmsRole> specification = createSpecification(criteria);
        return rmsRoleRepository.count(specification);
    }

    /**
     * Function to convert RmsRoleCriteria to a {@link Specification}
     */
    private Specification<RmsRole> createSpecification(RmsRoleCriteria criteria) {
        Specification<RmsRole> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RmsRole_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), RmsRole_.name));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), RmsRole_.serialNumber));
            }
            if (criteria.getSortString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSortString(), RmsRole_.sortString));
            }
            if (criteria.getDescString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescString(), RmsRole_.descString));
            }
            if (criteria.getImageBlobName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageBlobName(), RmsRole_.imageBlobName));
            }
            if (criteria.getUsingFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getUsingFlag(), RmsRole_.usingFlag));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), RmsRole_.remarks));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), RmsRole_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), RmsRole_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), RmsRole_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), RmsRole_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), RmsRole_.updateTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), RmsRole_.verifyTime));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(RmsRole_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(RmsRole_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(RmsRole_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(RmsRole_.parent, JoinType.LEFT).get(RmsRole_.id)));
            }
            if (criteria.getRmsNodeId() != null) {
                specification = specification.and(buildSpecification(criteria.getRmsNodeId(),
                    root -> root.join(RmsRole_.rmsNodes, JoinType.LEFT).get(RmsNode_.id)));
            }
            if (criteria.getRmsUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getRmsUserId(),
                    root -> root.join(RmsRole_.rmsUsers, JoinType.LEFT).get(RmsUser_.id)));
            }
        }
        return specification;
    }
}
