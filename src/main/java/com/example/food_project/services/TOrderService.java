package com.example.food_project.services;

import com.example.food_project.dto.TOrderDTO;
import com.example.food_project.entity.TOrderEntity;

import java.util.List;

public interface TOrderService {

    TOrderEntity newOrder(TOrderEntity tOrder);

    TOrderDTO findById(int idOrder);

    List<TOrderEntity> getListIdOrderByIdUser(int userId);
    List<TOrderDTO> findByListId(List<Integer> idTOrders);

    List<Integer> getTOrder(int idUser);
}
