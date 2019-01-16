package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.PlanInfoStep;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PlanInfoStep entity.
 */
public interface PlanInfoStepSearchRepository extends ElasticsearchRepository<PlanInfoStep, Long> {
}
