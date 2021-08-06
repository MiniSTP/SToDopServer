package com.stodop.demo.repositories;

import com.stodop.demo.model.Projects;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<Projects,String> {
}
