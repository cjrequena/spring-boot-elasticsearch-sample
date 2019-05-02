package com.sample.elasticsearch.repository;

import com.sample.elasticsearch.entity.FooEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

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
public interface FooRepository extends ElasticsearchRepository<FooEntity, String> {

}
