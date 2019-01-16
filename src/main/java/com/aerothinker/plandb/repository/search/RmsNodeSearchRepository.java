package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.RmsNode;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the RmsNode entity.
 */
public interface RmsNodeSearchRepository extends ElasticsearchRepository<RmsNode, Long> {
}
