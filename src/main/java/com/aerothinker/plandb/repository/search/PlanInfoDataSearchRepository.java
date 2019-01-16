package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.PlanInfoData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PlanInfoData entity.
 */
public interface PlanInfoDataSearchRepository extends ElasticsearchRepository<PlanInfoData, Long> {
}
