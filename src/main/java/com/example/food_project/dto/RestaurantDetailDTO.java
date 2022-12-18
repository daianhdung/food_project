package com.example.food_project.dto;


import com.example.food_project.entity.RestaurantReviewEntity;

import java.util.Set;

public class RestaurantDetailDTO {
    private int id;
    private String title;
    private String image;
    private float avgRate;
    private String desc;
    private String isFavor;
    private Set<RestaurantReviewEntity> reviewEntityList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(float avgRate) {
        this.avgRate = avgRate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<RestaurantReviewEntity> getReviewEntityList() {
        return reviewEntityList;
    }

    public void setReviewEntityList(Set<RestaurantReviewEntity> reviewEntityList) {
        this.reviewEntityList = reviewEntityList;
    }

    public String getIsFavor() {
        return isFavor;
    }

    public void setIsFavor(String isFavor) {
        this.isFavor = isFavor;
    }
}
