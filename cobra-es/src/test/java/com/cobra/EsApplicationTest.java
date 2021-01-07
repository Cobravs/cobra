//package com.cobra;
//
//import com.cobra.es.config.EsConfig;
//import com.cobra.es.test.dao.TestDao;
//import com.cobra.es.test.entity.ChildVo;
//import com.cobra.es.test.entity.TestEntity;
//import com.cobra.es.test.entity.Vo;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.index.IndexSettings;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.IndexOperations;
//import org.springframework.data.elasticsearch.core.document.Document;
//import org.springframework.data.elasticsearch.core.query.AliasQuery;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@SpringBootTest
//@Slf4j
//public class EsApplicationTest {
//
//    @Autowired
//    TestDao testDao;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void testAdd() {
//        TestEntity entity = new TestEntity();
//        entity.setId(UUID.randomUUID().toString());
//        entity.setName(UUID.randomUUID().toString());
//        entity.setCallId(UUID.randomUUID().toString());
//        entity.setValue(UUID.randomUUID().toString());
//        ChildVo childVo = new ChildVo();
//        childVo.setChildName("child");
//        childVo.setId("1");
//        entity.setChildVo(childVo);
//        List<Vo> list = new ArrayList<>();
//        Vo vo1 = new Vo();
//        vo1.setId("vo1");
//        vo1.setVoName("vo1");
//        list.add(vo1);
//        Vo vo2 = new Vo();
//        vo2.setVoName("vo2");
//        vo2.setId("vo2");
//        list.add(vo2);
//        entity.setVoList(list);
//        entity.setNewKey("newkey");
//        entity.setField2("field2");
//        System.out.println("entity.toString() = " + entity.toString());
//        testDao.save(entity);
//    }
//
//    @Test
//    void findByName() {
//        String name = "fce36f21-ce98-4c0f-9e1c-714cac08c0de";
//        TestEntity byName = testDao.findByName(name);
//        log.info(byName.toString());
//    }
//
//    @Autowired
//    private ElasticsearchRestTemplate template;
//    //    @Autowired
////    @Qualifier("abstractDefaultIndexOperations")
////    private IndexOperations indexOperations;
////    void createIndex(){
////        indexOperations.createMapping(TestEntity.class);
////        boolean b = indexOperations.create();
////        template.createIndex(TestEntity.class);
////    }
//    @Autowired
//    ElasticsearchOperations elasticsearchOperations;
//
//    @Test
//    void testDeleteIndex() {
//        IndexOperations indexOperations = elasticsearchOperations.indexOps(TestEntity.class);
//        boolean delete = indexOperations.delete();
//        log.info("delete = {}", delete);
//    }
//
//    @Autowired
//    private EsConfig config;
//    @Value("${spring.elasticsearch.max-result-window}")
//    private long maxResultWindow = 10000000L;
//
//    @Test
//    void testCreateIndex() {
//        IndexOperations indexOperations = elasticsearchOperations.indexOps(TestEntity.class);
//        boolean exists = indexOperations.exists();
//        if (!exists) {
//            // 创建索引
//            indexOperations.create(Document.create().append(IndexSettings.MAX_RESULT_WINDOW_SETTING.getKey(), maxResultWindow));
//            indexOperations.createMapping(TestEntity.class);
//            // 添加别名
//            indexOperations.addAlias(new AliasQuery(config.getCallIndexNamePrefix()));
//            log.info("创建索引：{}", config.getTestIndexName());
//
//        }
//    }
//    @Test
//    void testAddIndex() {
//        IndexOperations indexOperations = elasticsearchOperations.indexOps(TestEntity.class);
//        boolean exists = indexOperations.exists();
//        if (!exists) {
//            // 创建索引
//            indexOperations.create(Document.create().append(IndexSettings.MAX_RESULT_WINDOW_SETTING.getKey(), maxResultWindow));
//            indexOperations.createMapping(TestEntity.class);
//            // 添加别名
//            indexOperations.addAlias(new AliasQuery(config.getCallIndexNamePrefix()));
//            log.info("创建索引：{}", config.getTestIndexName());
//
//        }
//    }
//}
