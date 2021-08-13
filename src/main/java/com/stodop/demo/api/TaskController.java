package com.stodop.demo.api;

import com.stodop.demo.component.JwtTokenProvider;
import com.stodop.demo.dao.TaskDao;
import com.stodop.demo.model.Tasks;
import com.stodop.demo.utils.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping(path = "/api/v1/task")
public class TaskController {
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @PostMapping(path="/addTask")
    public ResponseEntity<Object> addTask(@RequestBody Tasks tasks, @RequestHeader("Authorization") String headerToken){
        try{
            if(jwtTokenProvider.validateToken(headerToken)){
                tasks.setAcchived(false);
                List<String> subTaskId=new ArrayList<>();
                List<String> commentId=new ArrayList<>();
                List<String> activityId=new ArrayList<>();
                if(tasks.getLable()==null){
                    tasks.setLable("");
                }
                if(tasks.getPriority()==null){
                    tasks.setPriority("p1");
                }
                tasks.setSubTasksId(subTaskId);
                tasks.setCommentsId(commentId);
                tasks.setActivitysId(activityId);
                taskDao.save(tasks);
                return Responses.generateResponse("Add Task Successfully!", HttpStatus.OK, tasks);
            }
            else{
                throw new Exception("You Have Not Authenticated!");
            }
        }catch (Exception e){
            return Responses.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
    @PutMapping
    public ResponseEntity<Object> editTask(@RequestParam(name="id") String taskId,@RequestBody Tasks tasks, @RequestHeader("Authorization") String headerToken){
        try{
            if(jwtTokenProvider.validateToken(headerToken)){
                Tasks taskInDb= taskDao.findById(taskId);
                if(taskInDb==null){
                    throw new Exception("Task Cannot Found!");
                }
                taskInDb.setActivitysId(tasks.getActivitysId());
                taskInDb.setPriority(tasks.getPriority());
                taskInDb.setLable(tasks.getLable());
                taskInDb.setSubTasksId(tasks.getSubTasksId());
                taskInDb.setAcchived(tasks.getAcchived());
                taskInDb.setCommentsId(tasks.getCommentsId());
                taskInDb.setDescription(tasks.getDescription());
                taskInDb.setSchedule(tasks.getSchedule());
                taskInDb.setTitle(tasks.getTitle());
                taskDao.save(taskInDb);
                return Responses.generateResponse("Update Task Successfully!", HttpStatus.OK, taskInDb);
            }
            else{
                throw new Exception("You Have Not Authenticated!");
            }
        }catch (Exception e){
            return Responses.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
