package com.sample.elasticsearch.service;

import com.sample.elasticsearch.entity.FooEntity;
import com.sample.elasticsearch.repository.FooRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class FooService {

  @Autowired
  private FooRepository fooRepository;

  public void save(FooEntity entity) throws Exception {
    this.fooRepository.save(entity);
  }

  public void deleteAll() throws Exception {
    this.fooRepository.deleteAll();
  }

}
