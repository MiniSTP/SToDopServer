package com.stodop.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class Projects {
    @Id
    private String id;
    @Field
    private String projectName;
    @Field
    private String comment;
    @Field
    private String viewType;
    @Field
    private Boolean archived;
    @Field
    private String color;
    @Field
    private Boolean favorite;
    @Field
    private  List<String> sectionsId;
    public Projects(){};

    public Projects(String projectName, String comment, String viewType, Boolean archived, String color, Boolean favorite, List<String> sectionsId) {
        this.projectName = projectName;
        this.comment = comment;
        this.viewType = viewType;
        this.archived = archived;
        this.color = color;
        this.favorite = favorite;
        this.sectionsId = sectionsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public List<String> getSectionsId() {
        return sectionsId;
    }

    public void setSectionsId(List<String> sectionsId) {
        this.sectionsId = sectionsId;
    }
}
