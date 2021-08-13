package com.stodop.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class Users {
    @Id
    private String id;
    @Field
    private String userName;
    @Field
    private String email;
    @Field
    private String password;
    @Field
    private String avata;
    @Field
    private LocalDateTime passwordUpdateTime;
    @Field
    private List<String> projectsId;

    public Users(){}

    public Users(String userName, String email, String password, String avata, List<String> projectsId, LocalDateTime passwordUpdateTime) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.avata = avata;
        this.passwordUpdateTime=passwordUpdateTime;
        this.projectsId = projectsId;
    }

    public LocalDateTime getPasswordUpdateTime() {
        return passwordUpdateTime;
    }

    public void setPasswordUpdateTime(LocalDateTime passwordUpdateTime) {
        this.passwordUpdateTime = passwordUpdateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvata() {
        return avata;
    }

    public void setAvata(String avata) {
        this.avata = avata;
    }

    public List<String> getProjectsId() {
        return projectsId;
    }

    public void setProjectsId(List<String> projectsId) {
        this.projectsId = projectsId;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avata='" + avata + '\'' +
                ", passwordUpdateTime=" + passwordUpdateTime +
                ", projectsId=" + projectsId +
                '}';
    }
}
