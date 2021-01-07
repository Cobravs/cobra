package com.cobra.es.test.dao;

import com.cobra.es.test.entity.TestEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TestDao extends ElasticsearchRepository<TestEntity, String> {

    TestEntity findByName(String name);
}
