package com.example.food_project.services;

import com.example.food_project.entity.UserEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface UserService {

    UserEntity getUser(String email);

    UserEntity saveUser(UserEntity userEntity);

    UserEntity resetPassword(String email) throws MessagingException, UnsupportedEncodingException;

    UserEntity updateUser(UserEntity userEntity);
}
