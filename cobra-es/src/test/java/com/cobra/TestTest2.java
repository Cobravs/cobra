package com.cobra;

import com.cobra.es.config.EsConfig;
import com.cobra.es.test.dao.TestDao;
import com.cobra.es.test.entity.ChildVo;
import com.cobra.es.test.entity.TestEntity;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.IndexSettings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@Slf4j
public class TestTest2 {
    @Autowired
    private EsConfig config;
    @Value("${spring.elasticsearch.max-result-window}")
    private long maxResultWindow = 10000000L;
    @Autowired
    private ElasticsearchRestTemplate template;

    @Autowired
    TestDao testDao;

    @Test
    void testCreateIndex() throws InterruptedException {
//        ThreadPoolSingle.execute(() -> createEs("2020_08"));
//        ThreadPoolSingle.execute(() -> createEs("2020_09"));
//        ThreadPoolSingle.execute(() -> createEs("2020_10"));
//        ThreadPoolSingle.execute(() -> createEs("2020_11"));
//        Thread.sleep(10000);
        createEs("2020_08");
    }

    private void createEs(String suffix) {
        EsConfig.setTestIndexSuffix(suffix);
        Document append = Document.create().append(IndexSettings.MAX_RESULT_WINDOW_SETTING.getKey(), maxResultWindow);
        IndexOperations indexOperations = template.indexOps(TestEntity.class);
        boolean exists = indexOperations.exists();
        if (!exists) {
            // 创建索引
            indexOperations.create(append);
            indexOperations.putMapping(indexOperations.createMapping());
            // 添加别名
            log.info("创建索引：{}", config.getTestIndexName());

        }
    }

    @Test
    void testAddIndex() {
        log.info("添加test索引数据");
        EsConfig.setTestIndexSuffix("2020_08");
        TestEntity testEntity = new TestEntity("2", "1", "1", "test", new ChildVo(), null, "test",
                "test", LocalDateTime.now());
        testDao.save(testEntity);
        log.info("添加成功！");
    }

    @Test
    void testAdd2Index() {
        log.info("添加test索引数据");
        EsConfig.setTestIndexSuffix("2020_09");
        TestEntity testEntity = new TestEntity("2", "1", "1", "test", new ChildVo(), null, "test",
                "test", LocalDateTime.now());
        testDao.save(testEntity);
        log.info("添加成功！");
    }

    @Test
    void findList() {
        EsConfig.setTestIndexSuffix("2020_09");
        TestEntity testEntity = template.get("2", TestEntity.class);
        log.info(testEntity.toString());


    }

    @Test
    void findList2() {
        EsConfig.setTestIndexSuffix("2020_08");
        TestEntity testEntity = template.get("2", TestEntity.class);
        log.info(testEntity.toString());

    }

    @Test
    void findList3() {
        EsConfig.setTestIndexSuffix("2020_08");
        TestEntity testEntity = testDao.findById("2").orElse(null);
        log.info(testEntity.toString());

    }

    @Test
    void findList4() {
        EsConfig.setTestIndexSuffix("*");
        Iterable<TestEntity> all = testDao.findAll();
        for (TestEntity testEntity : all) {
            log.info(testEntity.toString());
        }

    }

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM");

    @Test
    void test111() {
        EsConfig.setTestIndexSuffix("*");
        String format = LocalDate.now().plusMonths(1).format(formatter);
        log.info(format);
    }
}
