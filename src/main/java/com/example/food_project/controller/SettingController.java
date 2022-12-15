package com.example.food_project.controller;

import com.example.food_project.services.UserService;
import com.example.food_project.util.AuthenticationUtil;
import com.example.food_project.util.PopupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.example.food_project.constants.ParamConstant.*;
import static com.example.food_project.constants.TemplateConstant.*;
import static com.example.food_project.constants.ViewConstant.HOME_VIEW;
import static com.example.food_project.constants.ViewConstant.SETTING_VIEW;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Controller
@RequestMapping(SETTING_VIEW)
public class SettingController {

    @Autowired
    AuthenticationUtil authenticationUtil;
    @Autowired
    PopupUtil popupUtil;
    @Autowired
    UserService userService;

    @GetMapping()
    public ModelAndView settingUser(){
        var mav = new ModelAndView(SETTING_TEMP);
        var authentication = getContext().getAuthentication();
        var user = userService.getUser(authentication.getName());
        mav.addObject(CLIENT_PARAM, user);
        mav.addObject(SIGNIN_PARAM, true);
        _isMsgShow = popupUtil.showPopup(mav);
        return mav;
    }
}
