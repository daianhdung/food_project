package com.example.food_project.entity;

import com.example.food_project.entity.id.RestaurantFavorId;

import javax.persistence.*;

@Entity(name = "restaurant_favor")
@IdClass(RestaurantFavorId.class)
public class RestaurantFavorEntity {

    @Id
    @Column(name = "id_user")
    private int idUser;
    @Id
    @Column(name = "id_restaurant")
    private int idRestaurant;

    @Column(name = "is_favor")
    private String isFavor;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", insertable = false, updatable = false)
    private RestaurantEntity restaurant;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }
}
