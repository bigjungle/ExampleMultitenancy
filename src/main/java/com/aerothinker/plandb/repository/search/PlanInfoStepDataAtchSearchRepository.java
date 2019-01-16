package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.PlanInfoStepDataAtch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PlanInfoStepDataAtch entity.
 */
public interface PlanInfoStepDataAtchSearchRepository extends ElasticsearchRepository<PlanInfoStepDataAtch, Long> {
}
