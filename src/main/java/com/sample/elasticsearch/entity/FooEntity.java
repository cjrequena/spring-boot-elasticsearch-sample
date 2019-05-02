package com.sample.elasticsearch.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

/**
 * <p>
 * <p>
 * <p>
 * <p>
 *
 * @author cjrequena
 * @version 1.0
 * @see
 * @since JDK1.8
 */
@ToString
@Data
@Document(indexName = "foo_index", type = "foo_type", shards = 1, replicas = 0, refreshInterval = "-1")
public class FooEntity implements Serializable {

  @Id
  @Field(type = Text)
  protected String id;

  @Field(type = Text)
  private String name;

  @Field(type = Text, index = false, analyzer = "standard")
  private String metaData1;

  @Field(type = Text)
  private String metaData2;

}