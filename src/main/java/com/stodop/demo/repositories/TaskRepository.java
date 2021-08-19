package com.stodop.demo.repositories;

import com.stodop.demo.model.Tasks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Tasks,String> {
}
