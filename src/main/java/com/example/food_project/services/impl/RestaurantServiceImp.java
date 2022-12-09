package com.example.food_project.services.impl;

import com.example.food_project.dto.RestaurantDTO;
import com.example.food_project.dto.RestaurantDetailDTO;
import com.example.food_project.entity.RestaurantEntity;
import com.example.food_project.entity.RestaurantReviewEntity;
import com.example.food_project.repository.RestaurantRepository;
import com.example.food_project.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;


    @Override
    public List<RestaurantDTO> getListRestaurant() {
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        List<RestaurantEntity> restaurantEntities = restaurantRepository.get6Restaurant();
        for (RestaurantEntity data: restaurantEntities) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setTitle(data.getName());
            restaurantDTO.setImage(data.getImage());
            float sumRate = 0;
            float avgRate = 0;
            for(RestaurantReviewEntity dataReview : data.getRestaurantReview()){
                sumRate += dataReview.getRate();
            }
            if(data.getRestaurantReview().size() > 0){
                avgRate = sumRate / data.getRestaurantReview().size();
            }
            restaurantDTO.setAvgRate(avgRate);
            restaurantDTOS.add(restaurantDTO);
            restaurantDTO.setReviewEntityList(data.getRestaurantReview());
        }
        return restaurantDTOS;
    }

    @Override
    public RestaurantDetailDTO getDetailRestaurant(int id) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
        if(restaurantEntity.isPresent()){
            RestaurantDetailDTO restaurantDetailDTO = new RestaurantDetailDTO();
            restaurantDetailDTO.setTitle(restaurantEntity.get().getName());
            restaurantDetailDTO.setTitle(restaurantEntity.get().getImage());
            float sumRate = 0;
            float avgRate = 0;
            for(RestaurantReviewEntity dataReview : restaurantEntity.get().getRestaurantReview()){
                sumRate += dataReview.getRate();
            }
            if(restaurantEntity.get().getRestaurantReview().size() > 0){
                avgRate = sumRate / restaurantEntity.get().getRestaurantReview().size();
            }
            restaurantDetailDTO.setAvgRate(avgRate);
            return restaurantDetailDTO;
        }
        return null;
    }
}
