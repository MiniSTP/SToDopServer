package com.stodop.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class comments {
    @Id
    private String id;
    @Field
    private String userId;
    @Field
    private String comment;
    @Field
    private Date time;

    public comments(){};

    public comments(String userId, String comment, Date time) {
        this.userId = userId;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "comments{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", comment='" + comment + '\'' +
                ", time=" + time +
                '}';
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
