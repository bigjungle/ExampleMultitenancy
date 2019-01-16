package com.aerothinker.plandb.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of PlanInfoStepSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class PlanInfoStepSearchRepositoryMockConfiguration {

    @MockBean
    private PlanInfoStepSearchRepository mockPlanInfoStepSearchRepository;

}
