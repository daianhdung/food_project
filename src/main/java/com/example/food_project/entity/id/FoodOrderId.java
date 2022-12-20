package com.example.food_project.entity.id;


import java.io.Serializable;

public class FoodOrderId implements Serializable {

    private int idOrder;
    private int idFood;

    public FoodOrderId(int idOrder, int idFood) {
        this.idOrder = idOrder;
        this.idFood = idFood;
    }
    public FoodOrderId(){}

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }
}
