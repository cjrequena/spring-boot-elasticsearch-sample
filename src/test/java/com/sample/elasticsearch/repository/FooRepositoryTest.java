package com.sample.elasticsearch.repository;

import com.sample.elasticsearch.MainApplication;
import com.sample.elasticsearch.entity.FooEntity;
import com.sample.elasticsearch.repository.FooRepository;
import lombok.extern.log4j.Log4j2;
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

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
public class FooRepositoryTest {

  @Autowired
  private FooRepository fooRepository;

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void should_save_the_entity_with_random_uuid() {

    // given
    String id = UUID.randomUUID().toString();
    FooEntity entity = new FooEntity();
    entity.setId(id);
    entity.setName("FooName1");
    entity.setMetaData1("MetaData1");
    entity.setMetaData2("MetaData2");
    fooRepository.save(entity);
    // when
    Optional<FooEntity> optional = fooRepository.findById(id);
    // then
    assertTrue(optional.isPresent());
    assertEquals(entity, optional.get());
  }

  @Test
  public void deleteAll() {
  }
}