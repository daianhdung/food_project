package com.example.food_project.services.impl;

import com.example.food_project.entity.UserEntity;
import com.example.food_project.repository.UserRepository;
import com.example.food_project.services.UserService;
import com.example.food_project.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StringUtil stringUtil;
    @Override
    public UserEntity getUser(String email) {
        return userRepository.findByEmail(email).size() > 0 ? userRepository.findByEmail(email).get(0) : null;
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        userEntity.setEmail(stringUtil.parseEmail(userEntity.getEmail()));
        userEntity.setPassword(passwordEncoder.encode(stringUtil.removeWhiteSpaceBeginAndEnd(userEntity.getPassword())));
        userEntity.setFullname(stringUtil.parseName(userEntity.getFullname()));
        return userRepository.save(userEntity);
    }
}
