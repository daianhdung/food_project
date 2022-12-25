package com.example.food_project.controller;

import com.example.food_project.services.CategoryService;
import com.example.food_project.services.FoodService;
import com.example.food_project.services.RestaurantService;
import com.example.food_project.services.UserService;
import com.example.food_project.util.AuthenticationUtil;
import com.example.food_project.util.PopupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.example.food_project.constants.ParamConstant.*;
import static com.example.food_project.constants.TemplateConstant.HOME_TEMP;
import static com.example.food_project.constants.TemplateConstant.SIGNIN_TEMP;
import static com.example.food_project.constants.ViewConstant.HOME_VIEW;
import static com.example.food_project.constants.ViewConstant.SIGNIN_VIEW;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;


@Controller
@CrossOrigin
@RequestMapping()
public class ApplicationController {
    @Autowired
    private AuthenticationUtil authenticationUtil;
    @Autowired
    PopupUtil popupUtil;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    FoodService foodService;
    @Autowired
    RestaurantService restaurantService;

    @GetMapping(SIGNIN_VIEW)
    public ModelAndView login(boolean error){
        if (authenticationUtil.getAccount() != null) {
            return new ModelAndView(REDIRECT_PREFIX + HOME_VIEW);
        }else {
            var mav = new ModelAndView(SIGNIN_TEMP);
            if(error){
                _isMsgShow = true;
                icon = ICON_ERROR_PARAM;
                _msg = "Tài khoản đăng nhập chưa đúng!";
            }
            _isMsgShow = popupUtil.showPopup(mav);
            return mav;
        }

    }


    @GetMapping(value = {HOME_VIEW, "/"})
    public ModelAndView index(){
        var client = authenticationUtil.getAccount();
        var mav = new ModelAndView(HOME_TEMP);
        mav.addObject("path", HOME_VIEW);
        var authentication = getContext().getAuthentication();
        mav.addObject(CATEGORY_PARAM, categoryService.getExplorerCategory());
        mav.addObject(RESTAURANT_PARAM, restaurantService.getListRestaurant());
        mav.addObject(FOOD_PARAM, foodService.get6Food());
        mav.addObject("listCategory", categoryService.getAll());

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
