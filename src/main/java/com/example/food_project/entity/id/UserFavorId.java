package com.example.food_project.entity.id;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserFavorId implements Serializable {


    private int idUser;
    private int idFood;

    private String isFavor;

    public UserFavorId(int idUser, int idFood, String isFavor) {
        this.idUser = idUser;
        this.idFood = idFood;
        this.isFavor = isFavor;
    }

    public UserFavorId(){}

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public String getIsFavor() {
        return isFavor;
    }

    public void setIsFavor(String isFavor) {
        this.isFavor = isFavor;
    }
}
