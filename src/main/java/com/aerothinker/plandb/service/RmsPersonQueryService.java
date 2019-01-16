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

import com.aerothinker.plandb.domain.RmsPerson;
import com.aerothinker.plandb.domain.*; // for static metamodels
import com.aerothinker.plandb.repository.RmsPersonRepository;
import com.aerothinker.plandb.repository.search.RmsPersonSearchRepository;
import com.aerothinker.plandb.service.dto.RmsPersonCriteria;
import com.aerothinker.plandb.service.dto.RmsPersonDTO;
import com.aerothinker.plandb.service.mapper.RmsPersonMapper;

/**
 * Service for executing complex queries for RmsPerson entities in the database.
 * The main input is a {@link RmsPersonCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RmsPersonDTO} or a {@link Page} of {@link RmsPersonDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RmsPersonQueryService extends QueryService<RmsPerson> {

    private final Logger log = LoggerFactory.getLogger(RmsPersonQueryService.class);

    private final RmsPersonRepository rmsPersonRepository;

    private final RmsPersonMapper rmsPersonMapper;

    private final RmsPersonSearchRepository rmsPersonSearchRepository;

    public RmsPersonQueryService(RmsPersonRepository rmsPersonRepository, RmsPersonMapper rmsPersonMapper, RmsPersonSearchRepository rmsPersonSearchRepository) {
        this.rmsPersonRepository = rmsPersonRepository;
        this.rmsPersonMapper = rmsPersonMapper;
        this.rmsPersonSearchRepository = rmsPersonSearchRepository;
    }

    /**
     * Return a {@link List} of {@link RmsPersonDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RmsPersonDTO> findByCriteria(RmsPersonCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RmsPerson> specification = createSpecification(criteria);
        return rmsPersonMapper.toDto(rmsPersonRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RmsPersonDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RmsPersonDTO> findByCriteria(RmsPersonCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RmsPerson> specification = createSpecification(criteria);
        return rmsPersonRepository.findAll(specification, page)
            .map(rmsPersonMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RmsPersonCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RmsPerson> specification = createSpecification(criteria);
        return rmsPersonRepository.count(specification);
    }

    /**
     * Function to convert RmsPersonCriteria to a {@link Specification}
     */
    private Specification<RmsPerson> createSpecification(RmsPersonCriteria criteria) {
        Specification<RmsPerson> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RmsPerson_.id));
            }
            if (criteria.getPersonName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPersonName(), RmsPerson_.personName));
            }
            if (criteria.getFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstName(), RmsPerson_.firstName));
            }
            if (criteria.getJobId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJobId(), RmsPerson_.jobId));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), RmsPerson_.lastName));
            }
            if (criteria.getOtherName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOtherName(), RmsPerson_.otherName));
            }
            if (criteria.getSex() != null) {
                specification = specification.and(buildSpecification(criteria.getSex(), RmsPerson_.sex));
            }
            if (criteria.getBirthday() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthday(), RmsPerson_.birthday));
            }
            if (criteria.getPic() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPic(), RmsPerson_.pic));
            }
            if (criteria.getIcon() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIcon(), RmsPerson_.icon));
            }
            if (criteria.getMobile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobile(), RmsPerson_.mobile));
            }
            if (criteria.getDepAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDepAddress(), RmsPerson_.depAddress));
            }
            if (criteria.getDepCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDepCode(), RmsPerson_.depCode));
            }
            if (criteria.getDutyId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDutyId(), RmsPerson_.dutyId));
            }
            if (criteria.getDimissionFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getDimissionFlag(), RmsPerson_.dimissionFlag));
            }
            if (criteria.getHeaderFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getHeaderFlag(), RmsPerson_.headerFlag));
            }
            if (criteria.getSatrapFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getSatrapFlag(), RmsPerson_.satrapFlag));
            }
            if (criteria.getLeaderFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getLeaderFlag(), RmsPerson_.leaderFlag));
            }
            if (criteria.getPostFlag1() != null) {
                specification = specification.and(buildSpecification(criteria.getPostFlag1(), RmsPerson_.postFlag1));
            }
            if (criteria.getPostFlag2() != null) {
                specification = specification.and(buildSpecification(criteria.getPostFlag2(), RmsPerson_.postFlag2));
            }
            if (criteria.getPostFlag3() != null) {
                specification = specification.and(buildSpecification(criteria.getPostFlag3(), RmsPerson_.postFlag3));
            }
            if (criteria.getOfficeTel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOfficeTel(), RmsPerson_.officeTel));
            }
            if (criteria.getFax() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFax(), RmsPerson_.fax));
            }
            if (criteria.getMail1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMail1(), RmsPerson_.mail1));
            }
            if (criteria.getMail2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMail2(), RmsPerson_.mail2));
            }
            if (criteria.getFamilyTel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamilyTel(), RmsPerson_.familyTel));
            }
            if (criteria.getFamilyAdd() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamilyAdd(), RmsPerson_.familyAdd));
            }
            if (criteria.getFamilyCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamilyCode(), RmsPerson_.familyCode));
            }
            if (criteria.getPersonDesc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPersonDesc(), RmsPerson_.personDesc));
            }
            if (criteria.getIdCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdCode(), RmsPerson_.idCode));
            }
            if (criteria.getPop3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPop3(), RmsPerson_.pop3));
            }
            if (criteria.getSmtp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSmtp(), RmsPerson_.smtp));
            }
            if (criteria.getMailUsername() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMailUsername(), RmsPerson_.mailUsername));
            }
            if (criteria.getMailPassword() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMailPassword(), RmsPerson_.mailPassword));
            }
            if (criteria.getMailKeepFlag() != null) {
                specification = specification.and(buildSpecification(criteria.getMailKeepFlag(), RmsPerson_.mailKeepFlag));
            }
            if (criteria.getPersonSort() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPersonSort(), RmsPerson_.personSort));
            }
            if (criteria.getLevelNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevelNum(), RmsPerson_.levelNum));
            }
            if (criteria.getPersonStateId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPersonStateId(), RmsPerson_.personStateId));
            }
            if (criteria.getBy01() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy01(), RmsPerson_.by01));
            }
            if (criteria.getBy02() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy02(), RmsPerson_.by02));
            }
            if (criteria.getBy03() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy03(), RmsPerson_.by03));
            }
            if (criteria.getBy04() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy04(), RmsPerson_.by04));
            }
            if (criteria.getBy05() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy05(), RmsPerson_.by05));
            }
            if (criteria.getBy06() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy06(), RmsPerson_.by06));
            }
            if (criteria.getBy07() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy07(), RmsPerson_.by07));
            }
            if (criteria.getBy08() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy08(), RmsPerson_.by08));
            }
            if (criteria.getBy09() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy09(), RmsPerson_.by09));
            }
            if (criteria.getBy10() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBy10(), RmsPerson_.by10));
            }
            if (criteria.getValidType() != null) {
                specification = specification.and(buildSpecification(criteria.getValidType(), RmsPerson_.validType));
            }
            if (criteria.getValidBegin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidBegin(), RmsPerson_.validBegin));
            }
            if (criteria.getValidEnd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidEnd(), RmsPerson_.validEnd));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), RmsPerson_.insertTime));
            }
            if (criteria.getUpdateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpdateTime(), RmsPerson_.updateTime));
            }
            if (criteria.getVerifyTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVerifyTime(), RmsPerson_.verifyTime));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), RmsPerson_.serialNumber));
            }
            if (criteria.getCreatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getCreatedById(),
                    root -> root.join(RmsPerson_.createdBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getModifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiedById(),
                    root -> root.join(RmsPerson_.modifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
            if (criteria.getVerifiedById() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedById(),
                    root -> root.join(RmsPerson_.verifiedBy, JoinType.LEFT).get(RmsUser_.id)));
            }
        }
        return specification;
    }
}
