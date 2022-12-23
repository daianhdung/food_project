package com.example.food_project.services.impl;

import com.example.food_project.entity.OrderStatusEntity;
import com.example.food_project.entity.TOrderEntity;
import com.example.food_project.repository.OrderStatusRepository;
import com.example.food_project.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static com.example.food_project.constants.ParamConstant.*;

@Service
public class OrderStatusServiceImp implements OrderStatusService {

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Override
    public OrderStatusEntity updateAllStatus(List<TOrderEntity> orderEntityList) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for(TOrderEntity data : orderEntityList){
            LocalDateTime orderDate = LocalDateTime.parse(data.getOrderDate(), formatter);
            Duration duration = Duration.between(orderDate, dateTimeNow);

            //New 1 oderstatus để handle status
            OrderStatusEntity orderStatus = new OrderStatusEntity();
            orderStatus.setIdOrder(data.getId());
            orderStatus.setActive("true");

            Optional<OrderStatusEntity> oldStatusPrepare = Optional.ofNullable(
                    orderStatusRepository.findByIdOrderAndIdStatus(data.getId(), STATUS_PREPARE_PARAM));
            Optional<OrderStatusEntity> oldStatusOnway = Optional.ofNullable(
                    orderStatusRepository.findByIdOrderAndIdStatus(data.getId(), STATUS_ONWAY_PARAM));
            Optional<OrderStatusEntity> oldStatusComplete = Optional.ofNullable(
                    orderStatusRepository.findByIdOrderAndIdStatus(data.getId(), STATUS_COMPLETE_PARAM));

            //Nếu thời gian hiện tại so với thời gian order chưa vượt quá 10s -> Prepare order
            if(duration.toSeconds() < 10){
                if(!oldStatusPrepare.isPresent()){
                    orderStatus.setIdStatus(STATUS_PREPARE_PARAM);
                    orderStatusRepository.save(orderStatus);
                }
            } else if (duration.toSeconds() > 10 && duration.toSeconds() < data.getEstimateShip()) {
                //Nếu thời gian hiện tại so với thời gian order chưa vượt thời gian estimate và lớn hơn 10s-> On the way
                if(oldStatusPrepare.isPresent()){
                    orderStatusRepository.updateStatus(data.getId(), STATUS_PREPARE_PARAM, "false");
                }
                if(!oldStatusOnway.isPresent()){
                    orderStatus.setIdStatus(STATUS_ONWAY_PARAM);
                    orderStatusRepository.save(orderStatus);
                }
            } else if (duration.toSeconds() > data.getEstimateShip()) {
                if(oldStatusPrepare.isPresent()){
                    orderStatusRepository.updateStatus(data.getId(), STATUS_PREPARE_PARAM, "false");
                }
                if(oldStatusOnway.isPresent()){
                    orderStatusRepository.updateStatus(data.getId(), STATUS_ONWAY_PARAM, "false");
                }
                if(!oldStatusComplete.isPresent()){
                    orderStatus.setIdStatus(STATUS_COMPLETE_PARAM);
                    orderStatusRepository.save(orderStatus);
                }
            }
        }
        return null;
    }

    @Override
    public List<Integer> findIdOrderByStatus(List<Integer> idOrder, int idStatus) {
        return orderStatusRepository.findIdOrderByStatus(idOrder, idStatus);
    }
}
