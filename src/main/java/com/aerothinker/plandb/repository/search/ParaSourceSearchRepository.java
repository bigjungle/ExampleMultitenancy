package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.ParaSource;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ParaSource entity.
 */
public interface ParaSourceSearchRepository extends ElasticsearchRepository<ParaSource, Long> {
}
