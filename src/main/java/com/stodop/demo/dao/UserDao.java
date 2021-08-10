package com.stodop.demo.dao;

import com.stodop.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    public void save(User user) {
        mongoTemplate.insert(user);
    }

    public User findById(final String userId) {
        return mongoTemplate.findById(userId, User.class);
    }

    public List<User> findByEmail(final String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.find(query, User.class);
    }
}
