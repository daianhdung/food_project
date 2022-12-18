package com.example.food_project.services.impl;

import com.example.food_project.entity.RestaurantFavorEntity;
import com.example.food_project.repository.RestaurantFavorRepository;
import com.example.food_project.services.RestaurantFavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantFavorServiceImp implements RestaurantFavorService {
    @Autowired
    RestaurantFavorRepository restaurantFavorRepository;

    @Override
    public RestaurantFavorEntity findByIdUserAndIdRestaurant(int idUser, int idRestaurant) {
        return restaurantFavorRepository.findByIdUserAndIdRestaurant(idUser, idRestaurant);
    }

    @Override
    public RestaurantFavorEntity updateFavorRes(RestaurantFavorEntity userFavor) {
        restaurantFavorRepository.updateFavorRes(userFavor.getIdUser(), userFavor.getIdRestaurant(), userFavor.getIsFavor());
        return null;
    }

    @Override
    public RestaurantFavorEntity addFavorRes(RestaurantFavorEntity userFavor) {
        return restaurantFavorRepository.save(userFavor);
    }

    @Override
    public List<Integer> getFavorRes(int userId) {
        return restaurantFavorRepository.getFavorRestaurant(userId);
    }
}
