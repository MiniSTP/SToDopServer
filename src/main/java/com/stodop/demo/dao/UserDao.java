package com.stodop.demo.dao;

import com.stodop.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Users> findAll() {
        return mongoTemplate.findAll(Users.class);
    }

    public void save(Users users) {
        mongoTemplate.insert(users);
    }

    public Users findById(final String userId) {
        return mongoTemplate.findById(userId, Users.class);
    }

    public List<Users> findByEmail(final String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.find(query, Users.class);
    }

    public void createNewUser(Users users){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<String> projectId = new ArrayList<>();
        users.setProjectsId(projectId);
        users.setPasswordUpdateTime(LocalDateTime.now());
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        if (users.getAvata()!=null) users.setAvata("");
        mongoTemplate.insert(users);
    }
}
