package com.example.food_project.controller;

import com.example.food_project.entity.UserEntity;
import com.example.food_project.services.UserService;
import com.example.food_project.util.AuthenticationUtil;
import com.example.food_project.util.PopupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.example.food_project.constants.TemplateConstant.*;
import static com.example.food_project.constants.ViewConstant.*;
import static com.example.food_project.constants.ParamConstant.*;

@Controller
@RequestMapping(SIGNUP_VIEW)
public class SignupController {


    @Autowired
    AuthenticationUtil authenticationUtil;
    @Autowired
    PopupUtil popupUtil;
    @Autowired
    UserService userService;

    @GetMapping()
    public ModelAndView signup(){
        if(authenticationUtil.getAccount() != null){
            return new ModelAndView(REDIRECT_PREFIX + HOME_VIEW);
        }else {
            var mav = new ModelAndView(SIGNUP_TEMP);
            _isMsgShow = popupUtil.showPopup(mav);
            return mav;
        }
    }

    @PostMapping()
    public String signup(UserEntity userEntity){
        _isMsgShow = true;
        if(userService.getUser(userEntity.getEmail()) != null){
            icon = ICON_ERROR_PARAM;
            _msg = "Email này đã được đăng kí";
            return REDIRECT_PREFIX + SIGNUP_VIEW;
        }else {
            userService.saveUser(userEntity);
            icon = ICON_SUCCESS_PARAM;
            _msg = "Đăng kí thành công";
            return REDIRECT_PREFIX + SIGNIN_VIEW;
        }
    }

}
