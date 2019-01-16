package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.ParaProp;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ParaProp entity.
 */
public interface ParaPropSearchRepository extends ElasticsearchRepository<ParaProp, Long> {
}
