package com.example.food_project.controller;

import com.example.food_project.services.FoodService;
import com.example.food_project.services.RestaurantService;
import com.example.food_project.services.UserService;
import com.example.food_project.util.AuthenticationUtil;
import com.example.food_project.util.PopupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.example.food_project.constants.TemplateConstant.*;
import static com.example.food_project.constants.ViewConstant.*;
import static com.example.food_project.constants.ParamConstant.*;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Controller
@RequestMapping(EXPLORE_VIEW)
public class ExploreController {

    @Autowired
    AuthenticationUtil authenticationUtil;
    @Autowired
    PopupUtil popupUtil;
    @Autowired
    UserService userService;
    @Autowired
    FoodService foodService;
    @Autowired
    RestaurantService restaurantService;

    @GetMapping()
    public ModelAndView index(){
        var client = authenticationUtil.getAccount();
        var mav = new ModelAndView(EXPLORE_TEMP);
        mav.addObject("path", EXPLORE_VIEW);
        var authentication = getContext().getAuthentication();
        mav.addObject(RESTAURANT_PARAM, restaurantService.getListRestaurant());
        mav.addObject(FOOD_PARAM, foodService.get6Food());
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
