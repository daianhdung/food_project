package com.example.food_project.controller;

import com.example.food_project.services.UserService;
import com.example.food_project.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.example.food_project.constants.ParamConstant.CLIENT_PARAM;
import static com.example.food_project.constants.ParamConstant.SIGNIN_PARAM;
import static com.example.food_project.constants.TemplateConstant.*;
import static com.example.food_project.constants.ViewConstant.*;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;


@Controller
@CrossOrigin
@RequestMapping()
public class ApplicationController {
    @Autowired
    private AuthenticationUtil authenticationUtil;
//    @Autowired
//    UserService userService;

    @GetMapping(SIGNIN_VIEW)
    public ModelAndView login(boolean error){
        var mav = new ModelAndView(SIGNIN_TEMP);
        if (error) {

        }
        return mav;
    }

    @GetMapping(value = {HOME_VIEW, "/"})
    public ModelAndView index(){
        var client = authenticationUtil.getAccount();
        var mav = new ModelAndView(HOME_TEMP);
        var authentication = getContext().getAuthentication();
        if(client != null){
            mav.addObject(CLIENT_PARAM, authentication.getName());
            mav.addObject(SIGNIN_PARAM, true);
        }else {
            mav.addObject(SIGNIN_PARAM, false);
        }
        return mav;
    }


}
