package com.example.food_project.services;

import com.example.food_project.entity.UserEntity;

public interface UserService {

    UserEntity getUser(String email);
}
