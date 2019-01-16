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

import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.RmsUserRepository;
import com.aerothinker.plandb.repository.search.RmsUserSearchRepository;
import com.aerothinker.plandb.service.dto.RmsUserCriteria;
import com.aerothinker.plandb.service.dto.RmsUserDTO;
import com.aerothinker.plandb.service.mapper.RmsUserMapper;

/**
 * Service for executing complex queries for RmsUser entities in the database.
 * The main input is a {@link RmsUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RmsUserDTO} or a {@link Page} of {@link RmsUserDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RmsUserQueryService extends QueryService<RmsUser> {

    private final Logger log = LoggerFactory.getLogger(RmsUserQueryService.class);

    private final RmsUserRepository rmsUserRepository;

    private final RmsUserMapper rmsUserMapper;

    private final RmsUserSearchRepository rmsUserSearchRepository;

    public RmsUserQueryService(RmsUserRepository rmsUserRepository, RmsUserMapper rmsUserMapper, RmsUserSearchRepository rmsUserSearchRepository) {
        this.rmsUserRepository = rmsUserRepository;
        this.rmsUserMapper = rmsUserMapper;
        this.rmsUserSearchRepository = rmsUserSearchRepository;
    }

    /**
     * Return a {@link List} of {@link RmsUserDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RmsUserDTO> findByCriteria(RmsUserCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RmsUser> specification = createSpecification(criteria);
        return rmsUserMapper.toDto(rmsUserRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RmsUserDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RmsUserDTO> findByCriteria(RmsUserCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RmsUser> specification = createSpecification(criteria);
        return rmsUserRepository.findAll(specification, page)
            .map(rmsUserMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RmsUserCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RmsUser> specification = createSpecification(criteria);
        return rmsUserRepository.count(specification);
    }

    /**
     * Function to convert RmsUserCriteria to a {@link Specification}
     */
    private Specification<RmsUser> createSpecification(RmsUserCriteria criteria) {
        Specification<RmsUser> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RmsUser_.id));
            }
            if (criteria.getUserName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserName(), RmsUser_.userName));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPersonId(), RmsUser_.personId));
            }
            if (criteria.getUserPassword() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserPassword(), RmsUser_.userPassword));
            }
            if (criteria.getProcessPassword() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProcessPassword(), RmsUser_.processPassword));
            }
            if (criteria.getUserSort() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserSort(), RmsUser_.userSort));
            }
            if (criteria.getUserDesc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserDesc(), RmsUser_.userDesc));
            }
            if (criteria.getUserPasswordValiInstantTimes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUserPasswordValiInstantTimes(), RmsUser_.userPasswordValiInstantTimes));
            }
            if (criteria.getUserPasswordLockFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getUserPasswordLockFlag(), RmsUser_.userPasswordLockFlag));
            }
            if (criteria.getProcPasswordValiInstantTimes() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProcPasswordValiInstantTimes(), RmsUser_.procPasswordValiInstantTimes));
            }
            if (criteria.getProcPasswordLockFlag() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProcPasswordLockFlag(), RmsUser_.procPasswordLockFlag));
            }
            if (criteria.getUserProp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserProp(), RmsUser_.userProp));
            }
            if (criteria.getBy01() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy01(), RmsUser_.by01));
            }
            if (criteria.getBy02() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy02(), RmsUser_.by02));
            }
            if (criteria.getBy03() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy03(), RmsUser_.by03));
            }
            if (criteria.getBy04() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy04(), RmsUser_.by04));
            }
            if (criteria.getBy05() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy05(), RmsUser_.by05));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), RmsUser_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), RmsUser_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), RmsUser_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), RmsUser_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), RmsUser_.updateTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), RmsUser_.verifyTime));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), RmsUser_.serialNumber));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(RmsUser_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(RmsUser_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(RmsUser_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getRmsRoleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRmsRoleId(),
                    root -> root.join(RmsUser_.rmsRoles, JoinType.LEFT).get(RmsRole_.id)));
            }
        }
        return specification;
    }
}
