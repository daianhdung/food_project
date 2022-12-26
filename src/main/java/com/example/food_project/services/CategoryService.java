package com.example.food_project.services;

import com.example.food_project.entity.CategoryEntity;
import com.example.food_project.entity.FoodEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> getExplorerCategory();

    List<CategoryEntity> getAll();

    List<Integer> findCategoryIdListByResFood(List<FoodEntity> foods);

    List<CategoryEntity> findByIdIsIn(List<Integer> listIdCategory);
}
