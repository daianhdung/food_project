package com.example.food_project.util;

import com.example.food_project.entity.UserEntity;
import com.example.food_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class AuthenticationUtil {
    @Autowired
    private UserService userService;

    public UserEntity getAccount(){
        var authentication = getContext().getAuthentication();
        return authentication instanceof AnonymousAuthenticationToken ? null
                : userService.getUser(authentication.getName());
    }
}
