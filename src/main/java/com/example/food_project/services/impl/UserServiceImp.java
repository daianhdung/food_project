package com.example.food_project.services.impl;

import com.example.food_project.entity.UserEntity;
import com.example.food_project.repository.UserRepository;
import com.example.food_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserEntity getUser(String email) {
        return userRepository.findByEmail(email).get(0);
    }
}
