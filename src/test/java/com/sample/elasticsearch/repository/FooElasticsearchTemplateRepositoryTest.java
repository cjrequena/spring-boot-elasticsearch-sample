package com.sample.elasticsearch.repository;

import com.sample.elasticsearch.MainApplication;
import com.sample.elasticsearch.entity.FooEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * <p>
 * <p>
 * <p>
 * <p>
 * @author cjrequena
 * @version 1.0
 * @since JDK1.8
 * @see
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MainApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FooElasticsearchTemplateRepositoryTest {

  @Autowired
  private FooElasticsearchTemplateRepository elasticsearchTemplateRepository;

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void save() throws Exception {
    // given
    String id = UUID.randomUUID().toString();
    FooEntity entity = new FooEntity();
    entity.setId(id);
    entity.setName("FooName1");
    entity.setMetaData1("MetaData1");
    entity.setMetaData2("MetaData2");
    elasticsearchTemplateRepository.save(entity);
  }
}