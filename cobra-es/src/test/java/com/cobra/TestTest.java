//package com.cobra;
//
//import com.cobra.es.config.EsConfig;
//import com.cobra.es.test.dao.TestDao;
//import com.cobra.es.test.entity.ChildVo;
//import com.cobra.es.test.entity.TestEntity;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.index.IndexSettings;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.IndexOperations;
//import org.springframework.data.elasticsearch.core.document.Document;
//import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
//import org.springframework.data.elasticsearch.core.query.AliasQuery;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.TemporalAccessor;
//import java.util.Date;
//
//@SpringBootTest
//@Slf4j
//public class TestTest {
//    @Autowired
//    private EsConfig config;
//    @Value("${spring.elasticsearch.max-result-window}")
//    private long maxResultWindow = 10000000L;
//    @Autowired
//    private ElasticsearchRestTemplate template;
//
//
//    @Autowired
//    TestDao testDao;
//
//    @Test
//    void testCreateIndex() {
//
//        Document append = Document.create().append(IndexSettings.MAX_RESULT_WINDOW_SETTING.getKey(), maxResultWindow);
//        IndexOperations indexOperations = template.indexOps(TestEntity.class);
//        boolean exists = indexOperations.exists();
//        if (!exists) {
//            // 创建索引
//            indexOperations.create(append);
//            indexOperations.putMapping(indexOperations.createMapping());
//            // 添加别名
//            indexOperations.addAlias(new AliasQuery(EsConfig.getCallIndexName()));
//            log.info("创建索引：{}", config.getTestIndexName());
//
//        }
//    }
//
//    @Test
//    void testTimes() {
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String timestamp = "2016-02-16 11:00:02";
//        TemporalAccessor temporalAccessor = formatter.parse(timestamp);
//        Instant result = Instant.from(temporalAccessor);
//        log.info("instant: {}", result);
//    }
//
//    @Test
//    void findAll() {
//        testDao.findAll().forEach(item -> {
//            log.info("item=", item.toString());
//        });
//    }
//
//    @Test
//    void findList() {
//        TestEntity testEntity = template.get("2", TestEntity.class);
//        log.info(testEntity.toString());
//        
//    }
//
//    @Test
//    void deleteTest() {
//        testDao.deleteById("2");
//    }
//
//    @Test
//    void testDateTime() {
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String format = now.format(formatter);
//        log.info("格式化：{}", format);
////        Instant.from()
//        LocalDateTime parse = LocalDateTime.parse(format, formatter);
//        log.info("parse:{}", parse);
//    }
//
//    @Test
//    void testCreateNextIndex() {
//        Document append = Document.create().append(IndexSettings.MAX_RESULT_WINDOW_SETTING.getKey(), maxResultWindow);
//        IndexOperations indexOperations = template.indexOps(IndexCoordinates.of(config.()));
//        boolean exists = indexOperations.exists();
//        if (!exists) {
//            // 创建索引
//            indexOperations.create(append);
//            indexOperations.putMapping(indexOperations.createMapping(TestEntity.class));
//            // 添加别名
//            indexOperations.addAlias(new AliasQuery(config.getTestIndexNamePrefix()));
//            log.info("创建下个月索引：{}", config.getTestNextIndexName());
//
//        }
//    }
//    @Test
//    void deleteNextIndex() {
//        IndexOperations indexOperations = template.indexOps(IndexCoordinates.of(config.getTestNextIndexName()));
//        boolean delete = indexOperations.delete();
//        log.info("delete = {}", delete);
//    }
//    @Test
//    void addAlias() {
//        IndexOperations indexOperations = template.indexOps(TestEntity.class);
//        boolean b = indexOperations.addAlias(new AliasQuery(config.getTestIndexNamePrefix()));
//        log.info("添加别名：{}", b);
//    }
//
//    @Test
//    void deleteAlias() {
//        IndexOperations indexOperations = template.indexOps(TestEntity.class);
//        boolean b = indexOperations.removeAlias(new AliasQuery(config.getCallIndexNamePrefix()));
//        log.info("删除别名：{}", b);
//    }
//
//    @Test
//    void deleteIndex() {
//        IndexOperations indexOperations = template.indexOps(TestEntity.class);
//        boolean delete = indexOperations.delete();
//        log.info("delete = {}", delete);
//    }
//
//    @Test
//    void testAddIndex() {
//        log.info("添加test索引数据");
//        TestEntity testEntity = new TestEntity("2", "1", "1", "test", new ChildVo(), null, "test", "test", new Date());
//        testDao.save(testEntity);
//        log.info("添加成功！");
//    }
//}
