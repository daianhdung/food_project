package com.example.food_project.services;

import com.example.food_project.entity.FoodOrderEntity;

import java.util.List;

public interface FoodOrderService {

    FoodOrderEntity insertNewListFoodOrder(int idOrder, List<FoodOrderEntity> orderEntityList);
}
