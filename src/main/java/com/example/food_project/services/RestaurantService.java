package com.example.food_project.services;

import com.example.food_project.dto.RestaurantDTO;
import com.example.food_project.dto.RestaurantDetailDTO;
import com.example.food_project.entity.RestaurantEntity;

import java.util.List;

public interface RestaurantService {

    List<RestaurantDTO> getListRestaurant();

    RestaurantDetailDTO getDetailRestaurant(int id);

    List<RestaurantDTO> findAllFavourRes(List<Integer> restaurantsId);
}
