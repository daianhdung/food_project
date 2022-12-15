package com.example.food_project.controller.api;

import com.example.food_project.entity.UserEntity;
import com.example.food_project.payload.response.DataResponse;
import com.example.food_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@RestController
@RequestMapping("/api")
public class UpdateProfileApi {
    @Autowired
    UserService userService;


    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserEntity userForm){
        var authentication = getContext().getAuthentication();
        var user = userService.getUser(authentication.getName());
        user.setFullname(userForm.getFullname());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEmail(userForm.getEmail());
        userService.updateUser(user);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("Thành công");
        dataResponse.setStatus(200);
        dataResponse.setSuccess(true);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
