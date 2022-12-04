package com.example.food_project.security;


import com.example.food_project.entity.UserEntity;
import com.example.food_project.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    LoginService loginService;
    //@Qualifier("") chỉ định tên Bean mà mình lấy trên container

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //Xử lí code đăng nhập thành công hay thất bại
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserEntity userEntity = loginService.checkLogin(userName);
        System.out.println("Thành công 1");
        if(userEntity != null){
            boolean isMatchPassword = passwordEncoder.matches(password, userEntity.getPassword());
            if(isMatchPassword){
                System.out.println("Thành công 2");
                return new UsernamePasswordAuthenticationToken(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
            }else {
                System.out.println("ko công 1");
                throw new Error();
            }
        }else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
