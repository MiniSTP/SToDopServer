package com.stodop.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class Sections {
    @Id
    private String id;
    @Field
    private String sectionName;
    @Field
    private List<String> tasksId;
    @Field
    private Boolean archived;

    public Sections(){};

    public Sections(String sectionName, List<String> tasksId, Boolean archived) {
        this.sectionName = sectionName;
        this.tasksId = tasksId;
        this.archived = archived;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<String> getTasksId() {
        return tasksId;
    }

    public void setTasksId(List<String> tasksId) {
        this.tasksId = tasksId;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    @Override
    public String toString() {
        return "Sections{" +
                "id='" + id + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", tasksId=" + tasksId +
                ", archived=" + archived +
                '}';
    }
}
