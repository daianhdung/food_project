package com.example.food_project.services.impl;

import com.example.food_project.dto.FoodDTO;
import com.example.food_project.dto.RestaurantDetailDTO;
import com.example.food_project.entity.FoodEntity;
import com.example.food_project.repository.FoodRepository;
import com.example.food_project.services.FoodService;
import com.example.food_project.services.RestaurantService;
import com.example.food_project.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    StringUtil stringUtil;

    @Override
    public long countAllFoodbyCategoryId(int categoryId) {
        return foodRepository.countAllByCategoryId(categoryId);
    }

    public List<FoodDTO> findAllbyCategory(int categoryId){
        List<FoodDTO> foodDTOList = new ArrayList<>();
        List<FoodEntity> foodEntities = new ArrayList<>();
        if(categoryId == 0){
            foodEntities = foodRepository.findAll();
        }else {
            foodEntities = foodRepository.findAllByCategoryId(categoryId);
        }
        for(var data : foodEntities){
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setImg(data.getImage());
            foodDTO.setName(data.getName());
            RestaurantDetailDTO restaurantDTO = restaurantService.getDetailRestaurant(data.getId());
            foodDTO.setRestaurantName(restaurantDTO.getTitle());
            foodDTOList.add(foodDTO);
        }
        return foodDTOList;
    }

    @Override
    public FoodDTO findById(int id) {
        Optional<FoodEntity> food = foodRepository.findById(id);
        if(food.isPresent()){
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setId(food.get().getId());
            foodDTO.setName(food.get().getName());
            foodDTO.setImg(food.get().getImage());
            foodDTO.setPrice(food.get().getPrice());
            foodDTO.setFoodAddonEntities(food.get().getFoodAddon());
            return foodDTO;
        }
        return null;
    }

    @Override
    public List<FoodDTO> findAllFavourite(List<Integer> foodIds) {
        List<FoodDTO> foodDTOList = new ArrayList<>();
        List<FoodEntity> foodEntities = foodRepository.findByIdIsIn(foodIds);
        for(var data : foodEntities){
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setId(data.getId());
            foodDTO.setImg(data.getImage());
            foodDTO.setName(data.getName());
            foodDTOList.add(foodDTO);
        }
        return foodDTOList;
    }

    @Override
    public List<FoodDTO> findByKeyword(String name) {
        List<FoodDTO> foodDTOList = new ArrayList<>();
        List<FoodEntity> foodEntities = new ArrayList<>();
        name = stringUtil.removeWhiteSpaceBeginAndEnd(name);
        name = "%" + name + "%";
        foodEntities = foodRepository.findByKeyword(name);
        for(var data : foodEntities){
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setImg(data.getImage());
            foodDTO.setName(data.getName());
            RestaurantDetailDTO restaurantDTO = restaurantService.getDetailRestaurant(data.getId());
            foodDTO.setRestaurantName(restaurantDTO.getTitle());
            foodDTOList.add(foodDTO);
        }
        return foodDTOList;
    }
}
