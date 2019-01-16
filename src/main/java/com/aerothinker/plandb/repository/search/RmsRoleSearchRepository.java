package com.aerothinker.plandb.repository.search;

import com.aerothinker.plandb.domain.RmsRole;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the RmsRole entity.
 */
public interface RmsRoleSearchRepository extends ElasticsearchRepository<RmsRole, Long> {
}
