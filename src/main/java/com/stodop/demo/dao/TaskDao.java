package com.stodop.demo.dao;

import com.stodop.demo.model.Sections;
import com.stodop.demo.model.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Tasks> findAll(){
        return mongoTemplate.findAll(Tasks.class);
    }
    public Tasks findById(String taskId){
        return mongoTemplate.findById(taskId,Tasks.class);
    }
    public List<Tasks> findBySectionId(String sectionId){
        Sections sections= mongoTemplate.findById(sectionId,Sections.class);
        List<String> taskIs=sections.getTasksId();
        List<Tasks> tasks=new ArrayList<>();
        for(String id:taskIs){
            tasks.add(mongoTemplate.findById(id,Tasks.class));
        }
        return tasks;
    }
    public void save(Tasks tasks){
        mongoTemplate.save(tasks);
    }
}
