package com.example.food_project.controller.api;

import com.example.food_project.dto.FoodDTO;
import com.example.food_project.payload.response.DataResponse;
import com.example.food_project.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AddFoodApi {
    @Autowired
    FoodService foodService;

    @GetMapping("food/{id}")
    public ResponseEntity<?> addFood(@PathVariable("id") int foodId){
        FoodDTO foodDTO = foodService.findById(foodId);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(true);
        dataResponse.setData(foodDTO);
        dataResponse.setStatus(200);

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
