package com.chen.mywebapp.repository;

import com.chen.mywebapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserMongoRepositoryImpl implements UserMongoRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public User findUserByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public void updateUser(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));

        Update update = new Update();
        update.set("age", 23);

        mongoTemplate.updateFirst(query, update, User.class);
    }

    @Override
    public void removeUser(User user) {
        mongoTemplate.remove(user);
    }
}
