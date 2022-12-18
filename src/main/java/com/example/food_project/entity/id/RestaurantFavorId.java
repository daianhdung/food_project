package com.example.food_project.entity.id;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RestaurantFavorId implements Serializable {

    private int idUser;

    private int idRestaurant;

    private String isFavor;

    public RestaurantFavorId(int idUser, int idRestaurant, String isFavor) {
        this.idUser = idUser;
        this.idRestaurant = idRestaurant;
        this.isFavor = isFavor;
    }

    public RestaurantFavorId(){}

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getIsFavor() {
        return isFavor;
    }

    public void setIsFavor(String isFavor) {
        this.isFavor = isFavor;
    }
}
