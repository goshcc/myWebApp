package com.chen.mywebapp.repository;


import com.chen.mywebapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BaseMongoRepository<T> {
    @Autowired
    MongoTemplate mongoTemplate;

    public T findByParams(Class<T> c, Map<String, Object> params) {
         Query query = new Query(Criteria.where("name").is("name"));
         T t = mongoTemplate.findOne(query, c);
         return t;
    }

    public void save(T t) {
        mongoTemplate.save(t);
    }

    public void update(Class<T> c, Map<String, Object> params) {
        Query query = new Query(Criteria.where("id").is("a"));
        Update update = new Update();
        update.set("age", 23);
        mongoTemplate.updateFirst(query, update, c);
    }

    public void remove(T t) {
        mongoTemplate.remove(t);
    }
}
