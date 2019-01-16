package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.ParaClass;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ParaClass entity.
 */
public interface ParaClassSearchRepository extends ElasticsearchRepository<ParaClass, Long> {
}
