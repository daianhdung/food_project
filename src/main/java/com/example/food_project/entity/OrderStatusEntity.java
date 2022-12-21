package com.example.food_project.entity;

import com.example.food_project.entity.id.OrderStatusId;

import javax.persistence.*;

@Entity(name = "order_status")
@IdClass(OrderStatusId.class)
public class OrderStatusEntity {

    @Id
    @Column(name = "id_order")
    private int idOrder;
    @Id
    @Column(name = "id_status")
    private int idStatus;
    @Column(name = "active")
    private String active;

    @ManyToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private TOrderEntity tOrder;

    @ManyToOne
    @JoinColumn(name = "id_status", insertable = false, updatable = false)
    private StatusEntity status;

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

    public TOrderEntity gettOrder() {
        return tOrder;
    }

    public void settOrder(TOrderEntity tOrder) {
        this.tOrder = tOrder;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
