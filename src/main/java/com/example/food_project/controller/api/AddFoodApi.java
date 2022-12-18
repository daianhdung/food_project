package com.example.food_project.controller.api;

import com.example.food_project.dto.FoodDTO;
import com.example.food_project.entity.UserFavorEntity;
import com.example.food_project.payload.response.DataResponse;
import com.example.food_project.services.FoodService;
import com.example.food_project.services.UserFavorService;
import com.example.food_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@RestController
@RequestMapping("api")
public class AddFoodApi {
    @Autowired
    FoodService foodService;
    @Autowired
    UserService userService;
    @Autowired
    UserFavorService userFavorService;

    @GetMapping("food/{id}")
    public ResponseEntity<?> addFood(@PathVariable("id") int foodId){
        FoodDTO foodDTO = foodService.findById(foodId);
        var authentication = getContext().getAuthentication();
        var user = userService.getUser(authentication.getName());
        UserFavorEntity favor = userFavorService.findByIdUserAndIdFood(user.getId(), foodId);
        if(favor != null){
            foodDTO.setIsFavor(favor.getIsFavor());
        }else {
            foodDTO.setIsFavor("false");
        }

        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(true);
        dataResponse.setData(foodDTO);
        dataResponse.setStatus(200);

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @PutMapping("/favor-toggle")
    public ResponseEntity<?> addFavor(@RequestBody UserFavorEntity userFavor){
        var authentication = getContext().getAuthentication();
        var user = userService.getUser(authentication.getName());
        Optional<UserFavorEntity> oldFavor = Optional.ofNullable(userFavorService.findByIdUserAndIdFood(user.getId(), userFavor.getIdFood()));
        if(oldFavor.isPresent()){
            userFavor.setIdFood(oldFavor.get().getIdFood());
            userFavor.setIdUser(oldFavor.get().getIdUser());
            userFavorService.updateFavor(userFavor);
        }else {
            userFavor.setIdUser(user.getId());
            userFavorService.addFavor(userFavor);
        }
//        userFavor.setIdUser(user.getId());
//        userFavorService.addFavor(userFavor);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(200);
        dataResponse.setSuccess(true);
        dataResponse.setData(userFavor.getIsFavor().equals("true"));

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
