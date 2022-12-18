package com.example.food_project.services.impl;

import com.example.food_project.entity.UserFavorEntity;
import com.example.food_project.repository.UserFavorRepository;
import com.example.food_project.services.UserFavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFavorServiceImp implements UserFavorService {

    @Autowired
    UserFavorRepository userFavorRepository;


    @Override
    public UserFavorEntity findByIdUserAndIdFood(int idUser, int idFood) {
        return userFavorRepository.findByIdUserAndIdFood(idUser, idFood);
    }

    @Override
    public UserFavorEntity updateFavor(UserFavorEntity userFavor) {
        userFavorRepository.updateFavor(userFavor.getIdUser(), userFavor.getIdFood(), userFavor.getIsFavor());
        return null;
    }
    public UserFavorEntity addFavor(UserFavorEntity userFavor){
        return userFavorRepository.save(userFavor);
    }
}
