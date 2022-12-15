package com.example.food_project.services.impl;

import com.example.food_project.dto.FoodDTO;
import com.example.food_project.dto.RestaurantDTO;
import com.example.food_project.dto.RestaurantDetailDTO;
import com.example.food_project.entity.FoodEntity;
import com.example.food_project.repository.FoodRepository;
import com.example.food_project.services.FoodService;
import com.example.food_project.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImp implements FoodService {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    RestaurantService restaurantService;
    @Override
    public List<FoodEntity> get6Food() {
        return foodRepository.get6Food();
    }

    @Override
    public long countAllFoodbyCategoryId(int categoryId) {
        return foodRepository.countAllByCategoryId(categoryId);
    }

    public List<FoodDTO> findAllbyCategory(int categoryId){
        List<FoodDTO> foodDTOList = new ArrayList<>();
        List<FoodEntity> foodEntities = foodRepository.findAllByCategoryId(categoryId);
        for(var data : foodEntities){
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setImg(data.getImage());
            foodDTO.setName(data.getName());
            RestaurantDetailDTO restaurantDTO = restaurantService.getDetailRestaurant(data.getId());
            foodDTO.setRestaurantName(restaurantDTO.getDesc());
            foodDTOList.add(foodDTO);
        }
        return foodDTOList;
    }
}
