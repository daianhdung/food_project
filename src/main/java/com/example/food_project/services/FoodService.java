package com.example.food_project.services;

import com.example.food_project.dto.FoodDTO;
import com.example.food_project.entity.FoodEntity;

import java.util.List;
import java.util.Optional;

public interface FoodService {

    List<FoodEntity> get6Food();

    long countAllFoodbyCategoryId(int categoryId);

    List<FoodDTO> findAllbyCategory(int categoryId);

    FoodDTO findById(int id);

    List<FoodDTO> findAllFavourite(List<Integer> foodIds);
}
