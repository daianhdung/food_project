package com.example.food_project.controller;

import com.example.food_project.services.UserService;
import com.example.food_project.util.AuthenticationUtil;
import com.example.food_project.util.PopupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

import static com.example.food_project.constants.ParamConstant.*;
import static com.example.food_project.constants.TemplateConstant.*;
import static com.example.food_project.constants.ViewConstant.*;

@Controller
@RequestMapping(RESET_PASSWORD_VIEW)
public class PasswordController {

    @Autowired
    AuthenticationUtil authenticationUtil;
    @Autowired
    PopupUtil popupUtil;
    @Autowired
    UserService userService;

    @GetMapping()
    public ModelAndView resetPassword(){
        if (authenticationUtil.getAccount() != null) {
            return new ModelAndView(REDIRECT_PREFIX + HOME_VIEW);
        }else {
            var mav = new ModelAndView(RESET_PASSWORD_TEMP);
            _isMsgShow = popupUtil.showPopup(mav);
            return mav;
        }
    }

    @PostMapping()
    public String sendPasswordReset(String email) throws MessagingException, UnsupportedEncodingException {
        _isMsgShow = true;
        if(userService.getUser(email) != null){
            userService.resetPassword(email);
            icon = ICON_SUCCESS_PARAM;
            _msg = "Mật khẩu đã được gửi tới email: " + email;
            return REDIRECT_PREFIX + SIGNIN_VIEW;
        }else {
            icon = ICON_ERROR_PARAM;
            _msg = "Email này chưa kích hoạt";
            return REDIRECT_PREFIX + RESET_PASSWORD_VIEW;
        }
    }
}
