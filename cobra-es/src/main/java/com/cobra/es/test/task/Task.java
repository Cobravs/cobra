//package com.cobra.es.test.task;
//
//import com.cobra.es.config.EsConfig;
//import com.cobra.es.test.entity.TaskEntity;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.index.IndexSettings;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.IndexOperations;
//import org.springframework.data.elasticsearch.core.document.Document;
//import org.springframework.data.elasticsearch.core.query.AliasQuery;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@EnableScheduling
//@Slf4j
//@Component
//public class Task {
//
//    @Value("${spring.elasticsearch.max-result-window}")
//    private long maxResultWindow = 10000000L;
//    @Autowired
//    private EsConfig config;
//
//    @Autowired
//    ElasticsearchOperations elasticsearchOperations;
//
////    @Scheduled(initialDelay = 10000, fixedRate = 10000)
//    void createIndex() {
//        log.info("定时任务创建索引");
//        IndexOperations indexOperations = elasticsearchOperations.indexOps(TaskEntity.class);
//        boolean exists = indexOperations.exists();
//        if (!exists) {
//            // 创建索引
//            indexOperations.create(Document.create().append(IndexSettings.MAX_RESULT_WINDOW_SETTING.getKey(), maxResultWindow));
//            indexOperations.createMapping(TaskEntity.class);
//            // 添加别名
//            indexOperations.addAlias(new AliasQuery(config.getCallIndexNamePrefix()));
//            log.info("创建索引：{}", config.getCallIndexName());
//            
//        }
//
//
//    }
//}
