package com.example.food_project.controller;

import com.example.food_project.services.RestaurantService;
import com.example.food_project.services.UserService;
import com.example.food_project.util.AuthenticationUtil;
import com.example.food_project.util.PopupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.example.food_project.constants.ParamConstant.*;
import static com.example.food_project.constants.TemplateConstant.*;
import static com.example.food_project.constants.ViewConstant.*;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Controller
@RequestMapping(DETAIL_VIEW)
public class DetailRestaurantController {
    @Autowired
    AuthenticationUtil authenticationUtil;
    @Autowired
    PopupUtil popupUtil;
    @Autowired
    UserService userService;
    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/restaurant/{id}")
    public ModelAndView restaurantDetail(@PathVariable("id") int restaurantId){
        var client = authenticationUtil.getAccount();
        var mav = new ModelAndView(DETAIL_TEMP);
        var authentication = getContext().getAuthentication();
        if(client != null){
            var user = userService.getUser(authentication.getName());
            mav.addObject(CLIENT_PARAM, user);
            mav.addObject(SIGNIN_PARAM, true);
        }else {
            mav.addObject(SIGNIN_PARAM, false);
        }
        mav.addObject("restaurant", restaurantService.getDetailRestaurant(restaurantId));
        return mav;
    }
}
