package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.ParaType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ParaType entity.
 */
public interface ParaTypeSearchRepository extends ElasticsearchRepository<ParaType, Long> {
}
