package com.example.food_project.controller;

import com.example.food_project.entity.UserEntity;
import com.example.food_project.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.example.food_project.constants.TemplateConstant.SIGNUP_TEMP;
import static com.example.food_project.constants.ViewConstant.HOME_VIEW;

@Controller
@RequestMapping("/signup")
public class SignupController {


    @Autowired
    AuthenticationUtil authenticationUtil;

    @GetMapping()
    public ModelAndView signup(){
        if(authenticationUtil.getAccount() != null){
            return new ModelAndView("redirect:" + HOME_VIEW);
        }else {
            var mav = new ModelAndView(SIGNUP_TEMP);
            return mav;
        }
    }

    @PostMapping()
    public String signup(UserEntity userEntity){

        return "";
    }

}
