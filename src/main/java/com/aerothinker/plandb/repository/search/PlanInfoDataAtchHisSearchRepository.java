package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.PlanInfoDataAtchHis;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PlanInfoDataAtchHis entity.
 */
public interface PlanInfoDataAtchHisSearchRepository extends ElasticsearchRepository<PlanInfoDataAtchHis, Long> {
}
