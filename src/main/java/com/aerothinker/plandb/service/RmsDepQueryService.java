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

import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.RmsDepRepository;
import com.aerothinker.plandb.repository.search.RmsDepSearchRepository;
import com.aerothinker.plandb.service.dto.RmsDepCriteria;
import com.aerothinker.plandb.service.dto.RmsDepDTO;
import com.aerothinker.plandb.service.mapper.RmsDepMapper;

/**
 * Service for executing complex queries for RmsDep entities in the database.
 * The main input is a {@link RmsDepCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RmsDepDTO} or a {@link Page} of {@link RmsDepDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RmsDepQueryService extends QueryService<RmsDep> {

    private final Logger log = LoggerFactory.getLogger(RmsDepQueryService.class);

    private final RmsDepRepository rmsDepRepository;

    private final RmsDepMapper rmsDepMapper;

    private final RmsDepSearchRepository rmsDepSearchRepository;

    public RmsDepQueryService(RmsDepRepository rmsDepRepository, RmsDepMapper rmsDepMapper, RmsDepSearchRepository rmsDepSearchRepository) {
        this.rmsDepRepository = rmsDepRepository;
        this.rmsDepMapper = rmsDepMapper;
        this.rmsDepSearchRepository = rmsDepSearchRepository;
    }

    /**
     * Return a {@link List} of {@link RmsDepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RmsDepDTO> findByCriteria(RmsDepCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RmsDep> specification = createSpecification(criteria);
        return rmsDepMapper.toDto(rmsDepRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RmsDepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RmsDepDTO> findByCriteria(RmsDepCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RmsDep> specification = createSpecification(criteria);
        return rmsDepRepository.findAll(specification, page)
            .map(rmsDepMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RmsDepCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RmsDep> specification = createSpecification(criteria);
        return rmsDepRepository.count(specification);
    }

    /**
     * Function to convert RmsDepCriteria to a {@link Specification}
     */
    private Specification<RmsDep> createSpecification(RmsDepCriteria criteria) {
        Specification<RmsDep> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RmsDep_.id));
            }
            if (criteria.getDepName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDepName(), RmsDep_.depName));
            }
            if (criteria.getDepTypeId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDepTypeId(), RmsDep_.depTypeId));
            }
            if (criteria.getParentDepId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParentDepId(), RmsDep_.parentDepId));
            }
            if (criteria.getRepealFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getRepealFlag(), RmsDep_.repealFlag));
            }
            if (criteria.getLevelId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLevelId(), RmsDep_.levelId));
            }
            if (criteria.getDepSort() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDepSort(), RmsDep_.depSort));
            }
            if (criteria.getParentDepStr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParentDepStr(), RmsDep_.parentDepStr));
            }
            if (criteria.getChildDepStr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChildDepStr(), RmsDep_.childDepStr));
            }
            if (criteria.getDepDesc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDepDesc(), RmsDep_.depDesc));
            }
            if (criteria.getTel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTel(), RmsDep_.tel));
            }
            if (criteria.getFax() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFax(), RmsDep_.fax));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), RmsDep_.address));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), RmsDep_.code));
            }
            if (criteria.getInternet() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInternet(), RmsDep_.internet));
            }
            if (criteria.getMail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMail(), RmsDep_.mail));
            }
            if (criteria.getBy01() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy01(), RmsDep_.by01));
            }
            if (criteria.getBy02() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy02(), RmsDep_.by02));
            }
            if (criteria.getBy03() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy03(), RmsDep_.by03));
            }
            if (criteria.getBy04() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy04(), RmsDep_.by04));
            }
            if (criteria.getBy05() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy05(), RmsDep_.by05));
            }
            if (criteria.getBy06() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy06(), RmsDep_.by06));
            }
            if (criteria.getBy07() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy07(), RmsDep_.by07));
            }
            if (criteria.getBy08() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy08(), RmsDep_.by08));
            }
            if (criteria.getBy09() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy09(), RmsDep_.by09));
            }
            if (criteria.getBy10() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy10(), RmsDep_.by10));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), RmsDep_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), RmsDep_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), RmsDep_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), RmsDep_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), RmsDep_.updateTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), RmsDep_.verifyTime));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), RmsDep_.serialNumber));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(RmsDep_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(RmsDep_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(RmsDep_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(RmsDep_.parent, JoinType.LEFT).get(RmsDep_.id)));
            }
        }
        return specification;
    }
}
