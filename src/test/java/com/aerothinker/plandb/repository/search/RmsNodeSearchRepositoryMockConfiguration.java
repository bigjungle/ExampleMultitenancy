package com.aerothinker.plandb.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of RmsNodeSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class RmsNodeSearchRepositoryMockConfiguration {

    @MockBean
    private RmsNodeSearchRepository mockRmsNodeSearchRepository;

}
