package com.sample.elasticsearch.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.elasticsearch.entity.FooEntity;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
@Log4j2
public class FooRestHighLevelClientRepository {

  static String INDEX = "foo_index";
  static String TYPE = "foo_type";
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

  public void createIndexAndSave(FooEntity entity) throws Exception {
    INDEX = INDEX + "_" + DateTimeFormatter.ofPattern("yyyy.MM.dd").format(LocalDateTime.now());
    createIndex(INDEX, TYPE);
    IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, entity.getId()).source(objectMapper.convertValue(entity, Map.class));
    IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
  }

  public void createIndex(String indexName, String indexType) throws Exception {

    GetIndexRequest getIndexRequest = new GetIndexRequest();
    getIndexRequest.indices(indexName);
    boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    if (!exists) {
      CreateIndexRequest createIndexRequest = createIndexRequest = new CreateIndexRequest(indexName);
      //SETTINGS
      createIndexRequest.settings(Settings.builder()
        .put("index.number_of_shards", 3)
        .put("index.number_of_replicas", 2)
      );
      //MAPPINGS
      Map<String, Object> jsonMap = new HashMap<>();
      Map<String, Object> metaData1 = new HashMap<>();
      metaData1.put("type", "text");
      metaData1.put("index", false);
      metaData1.put("analyzer", "standard");
      //--

      Map<String, Object> properties = new HashMap<>();
      properties.put("metaData1", metaData1);
      Map<String, Object> mapping = new HashMap<>();
      mapping.put("properties", properties);
      jsonMap.put(indexType, mapping);
      createIndexRequest.mapping(indexType, jsonMap);
      CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
      boolean acknowledged = createIndexResponse.isAcknowledged();
      boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
      log.info("Create Index acknowledged= {},  shardsAcknowledged={}", acknowledged, shardsAcknowledged);
    } else {
      log.info("The Index {} alrady exists", indexName);
    }
  }

}
