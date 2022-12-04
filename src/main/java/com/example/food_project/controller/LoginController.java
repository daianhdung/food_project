package com.example.food_project.controller;

import com.example.food_project.jwt.JwtTokenHelper;
import com.example.food_project.payload.request.SignInRequest;
import com.example.food_project.payload.response.DataResponse;
import com.example.food_project.payload.response.DataTokenResponse;
import com.example.food_project.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

import static com.example.food_project.constants.ViewConstant.*;


@Controller
@CrossOrigin
@RequestMapping()
public class LoginController {
    @GetMapping(SIGNIN_VIEW)
    public ModelAndView login(HttpServletRequest request){
        return new ModelAndView("signin");
    }

    @GetMapping(HOME_VIEW)
    public ModelAndView index(){
        return new ModelAndView("index");
    }


}
