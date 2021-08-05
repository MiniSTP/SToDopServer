package com.stodop.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class Activitys {
    @Id
    private String id;
    @Field
    private String userId;
    @Field
    private String content;
    @Field
    private Date time;

    public Activitys(){};

    public Activitys(String userId, String content, Date time) {
        this.userId = userId;
        this.content = content;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Activitys{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
