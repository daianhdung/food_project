package com.example.food_project.dto;

import com.example.food_project.entity.FoodAddonEntity;

import java.util.Set;

public class FoodDTO {
    private int id;

    private String name;
    private String img;
    private int price;
    private String restaurantName;
    private String isFavor;

    private Set<FoodAddonEntity> foodAddonEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getIsFavor() {
        return isFavor;
    }

    public void setIsFavor(String isFavor) {
        this.isFavor = isFavor;
    }

    public Set<FoodAddonEntity> getFoodAddonEntities() {
        return foodAddonEntities;
    }

    public void setFoodAddonEntities(Set<FoodAddonEntity> foodAddonEntities) {
        this.foodAddonEntities = foodAddonEntities;
    }
}
