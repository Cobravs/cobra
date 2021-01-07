package com.cobra.es.test.dao;

import com.cobra.es.test.entity.SimpleEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SimpleDao extends ElasticsearchRepository<SimpleEntity, String> {
}
