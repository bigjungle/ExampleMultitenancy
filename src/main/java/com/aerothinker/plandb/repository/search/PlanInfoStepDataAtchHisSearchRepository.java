package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.PlanInfoStepDataAtchHis;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PlanInfoStepDataAtchHis entity.
 */
public interface PlanInfoStepDataAtchHisSearchRepository extends ElasticsearchRepository<PlanInfoStepDataAtchHis, Long> {
}
