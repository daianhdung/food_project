package com.example.food_project.services.impl;

import com.example.food_project.entity.FoodOrderEntity;
import com.example.food_project.repository.FoodOrderRepository;
import com.example.food_project.services.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodOrderServiceImp implements FoodOrderService {
    @Autowired
    FoodOrderRepository foodOrderRepository;

    @Override
    public FoodOrderEntity insertNewListFoodOrder(int idOrder, List<FoodOrderEntity> orderEntityList) {
        for(FoodOrderEntity data : orderEntityList){
            data.setIdOrder(idOrder);
            foodOrderRepository.save(data);
        }
        return null;
    }
}
