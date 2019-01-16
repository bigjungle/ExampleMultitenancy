package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.PlanInfoStepAtch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PlanInfoStepAtch entity.
 */
public interface PlanInfoStepAtchSearchRepository extends ElasticsearchRepository<PlanInfoStepAtch, Long> {
}
