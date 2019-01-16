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

import com.aerothinker.plandb.domain.VerifyRec;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.VerifyRecRepository;
import com.aerothinker.plandb.repository.search.VerifyRecSearchRepository;
import com.aerothinker.plandb.service.dto.VerifyRecCriteria;
import com.aerothinker.plandb.service.dto.VerifyRecDTO;
import com.aerothinker.plandb.service.mapper.VerifyRecMapper;

/**
 * Service for executing complex queries for VerifyRec entities in the database.
 * The main input is a {@link VerifyRecCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VerifyRecDTO} or a {@link Page} of {@link VerifyRecDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VerifyRecQueryService extends QueryService<VerifyRec> {

    private final Logger log = LoggerFactory.getLogger(VerifyRecQueryService.class);

    private final VerifyRecRepository verifyRecRepository;

    private final VerifyRecMapper verifyRecMapper;

    private final VerifyRecSearchRepository verifyRecSearchRepository;

    public VerifyRecQueryService(VerifyRecRepository verifyRecRepository, VerifyRecMapper verifyRecMapper, VerifyRecSearchRepository verifyRecSearchRepository) {
        this.verifyRecRepository = verifyRecRepository;
        this.verifyRecMapper = verifyRecMapper;
        this.verifyRecSearchRepository = verifyRecSearchRepository;
    }

    /**
     * Return a {@link List} of {@link VerifyRecDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VerifyRecDTO> findByCriteria(VerifyRecCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<VerifyRec> specification = createSpecification(criteria);
        return verifyRecMapper.toDto(verifyRecRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VerifyRecDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VerifyRecDTO> findByCriteria(VerifyRecCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<VerifyRec> specification = createSpecification(criteria);
        return verifyRecRepository.findAll(specification, page)
            .map(verifyRecMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VerifyRecCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<VerifyRec> specification = createSpecification(criteria);
        return verifyRecRepository.count(specification);
    }

    /**
     * Function to convert VerifyRecCriteria to a {@link Specification}
     */
    private Specification<VerifyRec> createSpecification(VerifyRecCriteria criteria) {
        Specification<VerifyRec> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), VerifyRec_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), VerifyRec_.name));
            }
            if (criteria.getDescString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescString(), VerifyRec_.descString));
            }
            if (criteria.getVerifyResult() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifyResult(), VerifyRec_.verifyResult));
            }
            if (criteria.getVerifyScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyScore(), VerifyRec_.verifyScore));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), VerifyRec_.remarks));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), VerifyRec_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), VerifyRec_.updateTime));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(VerifyRec_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(VerifyRec_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
        }
        return specification;
    }
}
