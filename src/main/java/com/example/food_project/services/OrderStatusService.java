package com.example.food_project.services;

import com.example.food_project.entity.OrderStatusEntity;
import com.example.food_project.entity.TOrderEntity;

import java.util.List;

public interface OrderStatusService {

    OrderStatusEntity updateAllStatus(List<TOrderEntity> orderEntityList);

    List<Integer> findIdOrderByStatus(List<Integer> idOrder, int idStatus);
}
