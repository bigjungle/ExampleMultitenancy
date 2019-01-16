package com.aerothinker.plandb.aop.company;

import com.aerothinker.plandb.security.SecurityUtils;
import com.aerothinker.plandb.repository.UserRepository;
import com.aerothinker.plandb.domain.User;
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

@Aspect
@Component
public class CompanyAspect {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private final String fieldName =  "companyId";

    private final Logger log = LoggerFactory.getLogger(CompanyAspect.class);

    /**
     * Run method if User service is hit.
     * Filter users based on which company the user is associated with.
     * Skip filter if user has no company
     */
    @Before("execution(* com.aerothinker.plandb.service.UserService.*(..)) || execution(* com.aerothinker.plandb.service.PlanInfoService.*(..)) || execution(* com.aerothinker.plandb.service.PlanInfoQueryService.*(..))")
    public void beforeExecution() throws Throwable {
        Optional<String> login = SecurityUtils.getCurrentUserLogin();

        if(login.isPresent()) {
			User user = userRepository.findOneByLogin(login.get()).get();

			if (user.getCompany() != null) {
                log.debug("use COMPANY_FILTER: {}", user);
                log.debug("use user.getCompany().getId: {}", user.getCompany().getId());
                Filter filter = entityManager.unwrap(Session.class).enableFilter("COMPANY_FILTER");
				filter.setParameter(fieldName, user.getCompany().getId());
			}
		}
    }
}
