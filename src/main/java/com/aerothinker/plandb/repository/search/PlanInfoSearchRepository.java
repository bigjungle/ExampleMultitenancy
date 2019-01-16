package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.PlanInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PlanInfo entity.
 */
public interface PlanInfoSearchRepository extends ElasticsearchRepository<PlanInfo, Long> {
}
