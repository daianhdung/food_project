package com.example.food_project.entity.id;

import java.io.Serializable;

public class UserFavorId implements Serializable {
    private int id_user;
    private int id_food;

    public UserFavorId(int id_user, int id_food) {
        this.id_user = id_user;
        this.id_food = id_food;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_food() {
        return id_food;
    }

    public void setId_food(int id_food) {
        this.id_food = id_food;
    }
}
