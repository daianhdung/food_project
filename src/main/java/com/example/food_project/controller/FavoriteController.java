package com.example.food_project.controller;

import com.example.food_project.services.*;
import com.example.food_project.util.AuthenticationUtil;
import com.example.food_project.util.PopupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.example.food_project.constants.ParamConstant.*;
import static com.example.food_project.constants.TemplateConstant.*;
import static com.example.food_project.constants.ViewConstant.*;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Controller
@RequestMapping(FAVORTITE_VIEW)
public class FavoriteController {

    @Autowired
    AuthenticationUtil authenticationUtil;
    @Autowired
    PopupUtil popupUtil;
    @Autowired
    UserService userService;
    @Autowired
    UserFavorService userFavorService;
    @Autowired
    FoodService foodService;
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    RestaurantFavorService restaurantFavorService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ModelAndView favoriteRestaurant(){
        var client = authenticationUtil.getAccount();
        var mav = new ModelAndView(FAVORTITE_TEMP);
        var authentication = getContext().getAuthentication();
        mav.addObject("path", FAVORTITE_VIEW);
        mav.addObject("listCategory", categoryService.getAll());
        if(client != null){
            var user = userService.getUser(authentication.getName());
            mav.addObject(CLIENT_PARAM, user);
            mav.addObject(SIGNIN_PARAM, true);
            List<Integer> restausId = restaurantFavorService.getFavorRes(client.getId());
            mav.addObject("restaurantSize", restausId.size());
            List<Integer> foodsId = userFavorService.getFavor(client.getId());
            mav.addObject("foodSize", foodsId.size());
            mav.addObject("restaurantsFavor", restaurantService.findAllFavourRes(restausId));
        }else {
            mav.addObject(SIGNIN_PARAM, false);
        }
        return mav;
    }

    @GetMapping("/food")
    public ModelAndView favoriteFood(){
        var client = authenticationUtil.getAccount();
        var mav = new ModelAndView(FAVORTITE_TEMP);
        var authentication = getContext().getAuthentication();
        mav.addObject("path", FAVORTITE_VIEW + "/food");
        if(client != null){
            var user = userService.getUser(authentication.getName());
            mav.addObject(CLIENT_PARAM, user);
            mav.addObject(SIGNIN_PARAM, true);
            List<Integer> restausId = restaurantFavorService.getFavorRes(client.getId());
            mav.addObject("restaurantSize", restausId.size());
            List<Integer> foodsId = userFavorService.getFavor(client.getId());
            mav.addObject("foodSize", foodsId.size());
            mav.addObject("foodsFavor", foodService.findAllFavourite(foodsId));
        }else {
            mav.addObject(SIGNIN_PARAM, false);
        }
        return mav;
    }

}
