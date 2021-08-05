package com.stodop.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

public class Tasks {
    @Id
    private String id;
    @Field
    private String titile;
    @Field
    private String description;
    @Field
    private Date schedule;
    @Field
    private List<String> subTasksId;
    @Field
    private List<String> commentsId;
    @Field
    private List<String> activitysId;
    @Field
    private Boolean acchived;
    @Field
    private String priority;
    @Field
    private String lable;

    public Tasks(){};

    public Tasks(String titile, String description, Date schedule, List<String> subTasksId, List<String> commentsId, List<String> activitysId, Boolean acchived, String priority, String lable) {
        this.titile = titile;
        this.description = description;
        this.schedule = schedule;
        this.subTasksId = subTasksId;
        this.commentsId = commentsId;
        this.activitysId = activitysId;
        this.acchived = acchived;
        this.priority = priority;
        this.lable = lable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "id='" + id + '\'' +
                ", titile='" + titile + '\'' +
                ", description='" + description + '\'' +
                ", schedule=" + schedule +
                ", subTasksId=" + subTasksId +
                ", commentsId=" + commentsId +
                ", activitysId=" + activitysId +
                ", acchived=" + acchived +
                ", priority='" + priority + '\'' +
                ", lable='" + lable + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public List<String> getSubTasksId() {
        return subTasksId;
    }

    public void setSubTasksId(List<String> subTasksId) {
        this.subTasksId = subTasksId;
    }

    public List<String> getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(List<String> commentsId) {
        this.commentsId = commentsId;
    }

    public List<String> getActivitysId() {
        return activitysId;
    }

    public void setActivitysId(List<String> activitysId) {
        this.activitysId = activitysId;
    }

    public Boolean getAcchived() {
        return acchived;
    }

    public void setAcchived(Boolean acchived) {
        this.acchived = acchived;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}
