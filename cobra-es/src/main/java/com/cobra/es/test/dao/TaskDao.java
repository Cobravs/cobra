package com.cobra.es.test.dao;

import com.cobra.es.test.entity.TaskEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TaskDao extends ElasticsearchRepository<TaskEntity, String> {
}
