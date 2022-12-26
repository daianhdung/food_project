package com.example.food_project.controller;

import com.example.food_project.services.CategoryService;
import com.example.food_project.services.UserService;
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
@RequestMapping(MESSAGE_VIEW)
public class MessageController {

    @Autowired
    AuthenticationUtil authenticationUtil;
    @Autowired
    PopupUtil popupUtil;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ModelAndView messageView(){
        var client = authenticationUtil.getAccount();
        var mav = new ModelAndView(MESSAGE_TEMP);
        var authentication = getContext().getAuthentication();
        mav.addObject("path", MESSAGE_VIEW);
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
