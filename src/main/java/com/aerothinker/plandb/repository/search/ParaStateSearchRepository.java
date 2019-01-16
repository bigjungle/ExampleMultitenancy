package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.ParaState;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ParaState entity.
 */
public interface ParaStateSearchRepository extends ElasticsearchRepository<ParaState, Long> {
}
