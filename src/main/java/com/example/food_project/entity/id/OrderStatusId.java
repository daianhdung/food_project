package com.example.food_project.entity.id;

import java.io.Serializable;

public class OrderStatusId implements Serializable {

    private int idOrder;
    private int idStatus;
    private String active;

    public OrderStatusId(int idOrder, int idStatus, String active) {
        this.idOrder = idOrder;
        this.idStatus = idStatus;
        this.active = active;
    }

    public OrderStatusId(){}
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
