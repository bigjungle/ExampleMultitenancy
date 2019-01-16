package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.PlanInfoDataHis;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PlanInfoDataHis entity.
 */
public interface PlanInfoDataHisSearchRepository extends ElasticsearchRepository<PlanInfoDataHis, Long> {
}
