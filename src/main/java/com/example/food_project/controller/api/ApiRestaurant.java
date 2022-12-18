package com.example.food_project.controller.api;

import com.example.food_project.entity.RestaurantFavorEntity;
import com.example.food_project.payload.response.DataResponse;
import com.example.food_project.services.RestaurantFavorService;
import com.example.food_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@RestController
@RequestMapping("/api")
public class ApiRestaurant {

    @Autowired
    UserService userService;

    @Autowired
    RestaurantFavorService restaurantFavorService;

    @PutMapping("/restaurant-favor/{id}")
    public ResponseEntity<?> addFavorRes(@PathVariable("id") int idRestau, @RequestBody RestaurantFavorEntity restaurantFavor){
        var authentication = getContext().getAuthentication();
        var user = userService.getUser(authentication.getName());
        Optional<RestaurantFavorEntity> oldRestaurantFavor = Optional.ofNullable(restaurantFavorService.
                findByIdUserAndIdRestaurant(user.getId(), idRestau));
        if(oldRestaurantFavor.isPresent()){
            restaurantFavor.setIdUser(user.getId());
            restaurantFavorService.updateFavorRes(restaurantFavor);
        }else {
            restaurantFavor.setIdUser(user.getId());
            restaurantFavorService.addFavorRes(restaurantFavor);
        }

        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(200);
        dataResponse.setSuccess(true);
        dataResponse.setData(restaurantFavor.getIsFavor().equals("true"));


        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
