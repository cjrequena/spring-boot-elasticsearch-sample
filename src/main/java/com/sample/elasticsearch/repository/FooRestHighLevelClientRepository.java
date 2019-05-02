package com.sample.elasticsearch.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.elasticsearch.entity.FooEntity;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
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
@Repository
public class FooRestHighLevelClientRepository {

  static final String INDEX = "foo_index";
  static final String TYPE = "foo_type";
  private RestHighLevelClient restHighLevelClient;
  private ObjectMapper objectMapper;

  @Autowired
  public FooRestHighLevelClientRepository(RestHighLevelClient restHighLevelClient, ObjectMapper objectMapper) {
    this.restHighLevelClient = restHighLevelClient;
    this.objectMapper = objectMapper;
  }

  public void save(FooEntity entity) throws Exception {
    IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, entity.getId()).source(objectMapper.convertValue(entity, Map.class));
    IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
  }

}
