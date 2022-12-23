package com.example.food_project.services.impl;

import com.example.food_project.dto.TOrderDTO;
import com.example.food_project.entity.TOrderEntity;
import com.example.food_project.repository.TOrderRepository;
import com.example.food_project.services.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TOrderServiceImp implements TOrderService {
    @Autowired
    TOrderRepository tOrderRepository;

    @Override
    public TOrderEntity newOrder(TOrderEntity tOrder) {
        return tOrderRepository.save(tOrder);
    }

    @Override
    public TOrderDTO findById(int idOrder) {
        Optional<TOrderEntity> tOrder = tOrderRepository.findById(idOrder);

        return null;
    }

    @Override
    public List<TOrderEntity> getListIdOrderByIdUser(int userId) {
        return tOrderRepository.getListIdOrderByIdUser(userId);
    }

    @Override
    public List<TOrderDTO> findByListId(List<Integer> idTOrders) {
        List<TOrderDTO> tOrderDTOS = new ArrayList<>();
        List<TOrderEntity> tOrderEntities = tOrderRepository.findByIdIsIn(idTOrders);

        LocalDateTime dateTimeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for(TOrderEntity data : tOrderEntities){
            LocalDateTime orderDate = LocalDateTime.parse(data.getOrderDate(), formatter);
            Duration duration = Duration.between(orderDate, dateTimeNow);
            TOrderDTO tOrderDTO = new TOrderDTO();
            tOrderDTO.setId(data.getId());

//            tOrderDTO.setEstimateShip(data.getEstimateShip() - (int) duration.toSeconds());
            LocalDateTime endDay = orderDate.plusSeconds(data.getEstimateShip());
            String endDayFormat = endDay.format(formatter);

            tOrderDTO.setEndShip(endDayFormat);

            tOrderDTO.setFoodName(data.getFoodOrder().iterator().next().getFood().getName());
            tOrderDTOS.add(tOrderDTO);
        }

        return tOrderDTOS;
    }

    @Override
    public List<Integer> getTOrder(int idUser) {
        return tOrderRepository.getTOrder(idUser);
    }


}
