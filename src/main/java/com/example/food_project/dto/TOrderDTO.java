package com.example.food_project.dto;

import com.example.food_project.entity.FoodOrderEntity;
import com.example.food_project.entity.StatusEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class TOrderDTO {

    private int id;
    private String foodName;
    private int estimateShip;
    private String statusName;
    private String endShip;
    private StatusEntity status;
    private Set<FoodOrderEntity> foodOrderEntities;

    public String getEndShip() {
        return endShip;
    }

    public void setEndShip(String endShip) {
        this.endShip = endShip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getEstimateShip() {
        return estimateShip;
    }

    public void setEstimateShip(int estimateShip) {
        this.estimateShip = estimateShip;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public Set<FoodOrderEntity> getFoodOrderEntities() {
        return foodOrderEntities;
    }

    public void setFoodOrderEntities(Set<FoodOrderEntity> foodOrderEntities) {
        this.foodOrderEntities = foodOrderEntities;
    }
}
