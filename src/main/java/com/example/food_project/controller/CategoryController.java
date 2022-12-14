package com.example.food_project.controller;

import com.example.food_project.entity.CategoryEntity;
import com.example.food_project.entity.FoodEntity;
import com.example.food_project.entity.RestaurantEntity;
import com.example.food_project.payload.response.DataResponse;
import com.example.food_project.services.CategoryService;
import com.example.food_project.services.FoodService;
import com.example.food_project.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    FoodService foodService;


}
