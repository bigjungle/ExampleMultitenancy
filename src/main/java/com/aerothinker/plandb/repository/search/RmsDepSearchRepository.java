package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.RmsDep;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the RmsDep entity.
 */
public interface RmsDepSearchRepository extends ElasticsearchRepository<RmsDep, Long> {
}
