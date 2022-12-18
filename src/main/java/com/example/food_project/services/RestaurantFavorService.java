package com.example.food_project.services;

import com.example.food_project.entity.RestaurantFavorEntity;

import java.util.List;

public interface RestaurantFavorService {

    RestaurantFavorEntity findByIdUserAndIdRestaurant(int idUser, int idRestaurant);

    RestaurantFavorEntity updateFavorRes(RestaurantFavorEntity userFavor);
    RestaurantFavorEntity addFavorRes(RestaurantFavorEntity userFavor);

    List<Integer> getFavorRes(int userId);
}
