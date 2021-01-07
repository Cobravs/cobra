package com.cobra;

import com.cobra.es.test.dao.SimpleDao;
import com.cobra.es.test.entity.SimpleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class SimpleTest {

    @Autowired
    private SimpleDao simpleDao;

    @Test
    public void save() {
        SimpleEntity entity = new SimpleEntity();
        entity.setId("1");
        entity.setDate(new Date());
        simpleDao.save(entity);

    }

    @Test
    public void findTest() {
        SimpleEntity entity = simpleDao.findById("1").get();

    }
}
