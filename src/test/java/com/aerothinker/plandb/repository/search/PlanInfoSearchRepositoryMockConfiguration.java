package com.aerothinker.plandb.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of PlanInfoSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class PlanInfoSearchRepositoryMockConfiguration {

    @MockBean
    private PlanInfoSearchRepository mockPlanInfoSearchRepository;

}
