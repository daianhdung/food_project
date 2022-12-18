package com.example.food_project.services;


import com.example.food_project.entity.UserFavorEntity;

import java.util.List;
import java.util.Optional;

public interface UserFavorService {

    UserFavorEntity findByIdUserAndIdFood(int idUser, int idFood);
    UserFavorEntity updateFavor(UserFavorEntity userFavor);
    UserFavorEntity addFavor(UserFavorEntity userFavor);
    List<Integer> getFavor(int userId);
}
