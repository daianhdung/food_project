package com.example.food_project.entity;

import com.example.food_project.entity.id.UserFavorId;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "user_favor")
@IdClass(UserFavorId.class)
public class UserFavorEntity {

    @Id
    @Column(name = "id_user")
    private int idUser;
    @Id
    @Column(name = "id_food")
    private int idFood;

    @Column(name = "is_favor")
    private String isFavor;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_food", insertable = false, updatable = false)
    private FoodEntity food;



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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

}
