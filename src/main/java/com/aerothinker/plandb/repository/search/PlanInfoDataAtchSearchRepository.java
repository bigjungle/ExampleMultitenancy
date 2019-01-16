package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.PlanInfoDataAtch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PlanInfoDataAtch entity.
 */
public interface PlanInfoDataAtchSearchRepository extends ElasticsearchRepository<PlanInfoDataAtch, Long> {
}
