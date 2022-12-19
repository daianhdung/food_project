package com.example.food_project.controller.api;

import com.example.food_project.entity.FoodOrderEntity;
import com.example.food_project.services.FoodService;
import com.example.food_project.services.UserFavorService;
import com.example.food_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CheckoutApi {

    @Autowired
    FoodService foodService;
    @Autowired
    UserService userService;
    @Autowired
    UserFavorService userFavorService;

    @PostMapping
    public ResponseEntity<?> orderFood(@RequestBody FoodOrderEntity foodOrder){


        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
