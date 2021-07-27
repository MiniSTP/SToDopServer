package com.stodop.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    List<Projects> projects;

    public Users(){}

    public Users(String userName, String email, String password, String avata, List<Projects> projects) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.avata = avata;
        this.projects = projects;
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

    public List<Projects> getProjects() {
        return projects;
    }

    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avata='" + avata + '\'' +
                ", projects=" + projects +
                '}';
    }
}
