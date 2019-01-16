package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.ParaAttr;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ParaAttr entity.
 */
public interface ParaAttrSearchRepository extends ElasticsearchRepository<ParaAttr, Long> {
}
