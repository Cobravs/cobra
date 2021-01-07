package com.cobra.es.test.dao;

import com.cobra.es.test.entity.EmailEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmailDao extends ElasticsearchRepository<EmailEntity, String> {
}
