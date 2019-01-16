package com.aerothinker.plandb.aop.company;

import com.aerothinker.plandb.domain.CompanyParameter;
import com.aerothinker.plandb.repository.PlanInfoRepository;
import com.aerothinker.plandb.security.SecurityUtils;
import com.aerothinker.plandb.repository.UserRepository;
import com.aerothinker.plandb.domain.User;
import com.aerothinker.plandb.domain.PlanInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Filter;
import java.util.Optional;
import java.util.NoSuchElementException;

@Aspect
@Component
public class PlanInfoAspect {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanInfoRepository planInfoRepository;
    @Autowired
    private CompanyParameter companyParameter;

    private final String fieldName =  "companyId";

    private final Logger log = LoggerFactory.getLogger(UserAspect.class);
    /**
     * Run method if PlanInfo repository save is hit.
     * Adds tenant information to entity.
     */
    @Before(value = "execution(* com.aerothinker.plandb.repository.PlanInfoRepository.save(..)) && args(planInfo, ..)")
    public void onSave(JoinPoint joinPoint, PlanInfo planInfo) {
        Optional<String> login = SecurityUtils.getCurrentUserLogin();

        if(login.isPresent()) {
            User loggedInUser = userRepository.findOneByLogin(login.get()).get();

            if (loggedInUser.getCompany() != null) {
                planInfo.setCompany(loggedInUser.getCompany());
            }
        }
    }

    /**
     * Run method if PlanInfo service findOne is hit.
     * Adds filtering to prevent display of information from another tenant
     */
    @Before(value = "execution(* com.aerothinker.plandb.service.PlanInfoService.findOne(..)) && args(id, ..)")
    public void onFindOne(JoinPoint joinPoint, Long id) throws Exception {
        Optional<String> login = SecurityUtils.getCurrentUserLogin();

        if(login.isPresent())
        {
            User loggedInUser = userRepository.findOneByLogin(login.get()).get();

            if (loggedInUser.getCompany() != null) {
                PlanInfo planInfo = planInfoRepository.findById(id).get();
                if(planInfo.getCompany() != loggedInUser.getCompany()){
                    throw new NoSuchElementException();
                }
            }
        }
    }
}
