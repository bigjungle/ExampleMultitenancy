package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.PlanInfoAtch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PlanInfoAtch entity.
 */
public interface PlanInfoAtchSearchRepository extends ElasticsearchRepository<PlanInfoAtch, Long> {
}
