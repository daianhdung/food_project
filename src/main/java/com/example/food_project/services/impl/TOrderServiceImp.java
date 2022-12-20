package com.example.food_project.services.impl;

import com.example.food_project.entity.TOrderEntity;
import com.example.food_project.repository.TOrderRepository;
import com.example.food_project.services.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOrderServiceImp implements TOrderService {
    @Autowired
    TOrderRepository tOrderRepository;

    @Override
    public TOrderEntity newOrder(TOrderEntity tOrder) {
        return tOrderRepository.save(tOrder);
    }
}
