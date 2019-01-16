package com.aerothinker.plandb.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.aerothinker.plandb.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.Company.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.Company.class.getName() + ".users", jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.PlanInfo.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.PlanInfoStep.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.PlanInfoAtch.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.PlanInfoStepAtch.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.PlanInfoData.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.PlanInfoStepData.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.PlanInfoDataAtch.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.PlanInfoStepDataAtch.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.PlanInfoDataHis.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.PlanInfoStepDataHis.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.PlanInfoDataAtchHis.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.PlanInfoStepDataAtchHis.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.VerifyRec.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.ParaType.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.ParaClass.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.ParaCat.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.ParaState.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.ParaSource.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.ParaProp.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.ParaAttr.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.RmsUser.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.RmsUser.class.getName() + ".rmsRoles", jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.RmsPerson.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.RmsDep.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.RmsRole.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.RmsRole.class.getName() + ".rmsNodes", jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.RmsRole.class.getName() + ".rmsUsers", jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.RmsNode.class.getName(), jcacheConfiguration);
            cm.createCache(com.aerothinker.plandb.domain.RmsNode.class.getName() + ".rmsRoles", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
