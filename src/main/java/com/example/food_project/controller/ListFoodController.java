package com.example.food_project.controller;

import com.example.food_project.services.FoodService;
import com.example.food_project.services.UserFavorService;
import com.example.food_project.services.UserService;
import com.example.food_project.util.AuthenticationUtil;
import com.example.food_project.util.PopupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.example.food_project.constants.ParamConstant.*;
import static com.example.food_project.constants.TemplateConstant.*;
import static com.example.food_project.constants.ViewConstant.*;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Controller
@RequestMapping(LISTING_VIEW)
public class ListFoodController {

    @Autowired
    AuthenticationUtil authenticationUtil;
    @Autowired
    PopupUtil popupUtil;
    @Autowired
    UserService userService;
    @Autowired
    FoodService foodService;


    @GetMapping()
    public ModelAndView listing(){
        var mav = new ModelAndView(LISTING_TEMP);
        return mav;
    }

    @GetMapping("/category/{id}")
    public ModelAndView listFoodbyCategory(@PathVariable("id") int categoryId){
        var authentication = getContext().getAuthentication();
        var client = authenticationUtil.getAccount();
        var mav = new ModelAndView(LISTING_TEMP);
        if(client != null){
            var user = userService.getUser(authentication.getName());
            mav.addObject(CLIENT_PARAM, user);
            mav.addObject(SIGNIN_PARAM, true);
        }else {
            mav.addObject(SIGNIN_PARAM, false);
        }
        return mav;
    }


}
