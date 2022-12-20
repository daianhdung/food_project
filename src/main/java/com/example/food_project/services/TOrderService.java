package com.example.food_project.services;

import com.example.food_project.entity.TOrderEntity;
import com.example.food_project.entity.UserEntity;

public interface TOrderService {

    TOrderEntity newOrder(TOrderEntity tOrder);
}
