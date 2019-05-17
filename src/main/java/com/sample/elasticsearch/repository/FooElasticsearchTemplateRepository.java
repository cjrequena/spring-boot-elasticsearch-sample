package com.sample.elasticsearch.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.elasticsearch.entity.FooEntity;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Repository;

import java.util.Map;

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
@Repository
public class FooElasticsearchTemplateRepository {

  static final String INDEX = "foo_index";
  static final String TYPE = "foo_type";
  private ElasticsearchTemplate elasticsearchTemplate;
  private ObjectMapper objectMapper;

  @Autowired
  public FooElasticsearchTemplateRepository(ElasticsearchTemplate elasticsearchTemplate, ObjectMapper objectMapper) {
    this.elasticsearchTemplate = elasticsearchTemplate;
    this.objectMapper = objectMapper;
  }

  public void save(FooEntity entity) throws Exception {
    IndexQuery fooQuery = new IndexQuery();
    fooQuery.setIndexName(INDEX);
    fooQuery.setType(TYPE);
    fooQuery.setObject(entity);
    log.info("User indexed: {}", elasticsearchTemplate.index(fooQuery));
    elasticsearchTemplate.refresh(INDEX);
  }
}
