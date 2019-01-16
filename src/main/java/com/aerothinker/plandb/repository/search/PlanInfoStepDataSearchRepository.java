package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.PlanInfoStepData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PlanInfoStepData entity.
 */
public interface PlanInfoStepDataSearchRepository extends ElasticsearchRepository<PlanInfoStepData, Long> {
}
