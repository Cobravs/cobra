package com.cobra;

import com.cobra.es.config.EsConfig;
import com.cobra.es.test.dao.EmailDao;
import com.cobra.es.test.entity.EmailEntity;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.IndexSettings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;

import java.util.Date;

@SpringBootTest
@Slf4j
public class EmailTest {
    @Autowired
    private EsConfig config;
    @Value("${spring.elasticsearch.max-result-window}")
    private long maxResultWindow = 10000000L;
    @Autowired
    private ElasticsearchRestTemplate template;
    @Autowired
    private EmailDao emailDao;

    @Test
    void testCreateIndex() {
        Document append = Document.create().append(IndexSettings.MAX_RESULT_WINDOW_SETTING.getKey(), maxResultWindow);
        IndexOperations indexOperations = template.indexOps(EmailEntity.class);
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
        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setId("1");
        emailEntity.setName("111");
        emailEntity.setDate(new Date());
        emailDao.save(emailEntity);
        log.info("添加成功！");
    }

    @Test
    void testFindById() {
        EmailEntity emailEntity = emailDao.findById("1").orElse(null);
        log.info(emailEntity.getId());
    }

    @Test
    void testFindAll() {
        Iterable<EmailEntity> emailEntity = emailDao.findAll();
        emailEntity.forEach(item -> {
            log.info(item.getId());
        });
    }
}
