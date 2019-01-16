package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.RmsPerson;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the RmsPerson entity.
 */
public interface RmsPersonSearchRepository extends ElasticsearchRepository<RmsPerson, Long> {
}
