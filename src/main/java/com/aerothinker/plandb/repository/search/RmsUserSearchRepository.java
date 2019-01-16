package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.RmsUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the RmsUser entity.
 */
public interface RmsUserSearchRepository extends ElasticsearchRepository<RmsUser, Long> {
}
