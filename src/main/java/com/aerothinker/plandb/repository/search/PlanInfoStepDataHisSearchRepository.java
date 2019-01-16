package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.PlanInfoStepDataHis;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PlanInfoStepDataHis entity.
 */
public interface PlanInfoStepDataHisSearchRepository extends ElasticsearchRepository<PlanInfoStepDataHis, Long> {
}
