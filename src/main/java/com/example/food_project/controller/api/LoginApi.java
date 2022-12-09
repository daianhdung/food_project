//package com.example.food_project.controller.api;
//
//
//import com.example.food_project.jwt.JwtTokenHelper;
//import com.example.food_project.payload.request.SignInRequest;
//import com.example.food_project.payload.response.DataResponse;
//import com.example.food_project.payload.response.DataTokenResponse;
//import com.example.food_project.services.LoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/api")
//public class LoginApi {
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    LoginService loginService;
//
//    @Autowired
//    JwtTokenHelper jwtTokenHelper;
//
//
//    //docblocks
//    /**
//     * @param
//     * @return
//     */
//    private final long expiredDate = 8 * 60 * 60 * 1000;
//    private final long refreshExpiredDate = 80 * 60 * 60 * 1000;
//
//    @PostMapping("/signin")
//    public ResponseEntity<?> signIn(@RequestBody SignInRequest request, HttpServletResponse response) throws IOException {
////        boolean isSuccess = loginService.checkLogin(request.getUsername(), request.getPassword());
//        System.out.println("Thành công api 1");
//
//            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
//                    request.getUsername(),request.getPassword());
//                Authentication auth = authenticationManager.authenticate(authRequest);
//                SecurityContext securityContext = SecurityContextHolder.getContext();
//                securityContext.setAuthentication(auth);
//                //        Generate token
//                String token = jwtTokenHelper.generateToken(request.getUsername(), "authen", expiredDate);
//                String refreshToken = jwtTokenHelper.generateToken(request.getUsername(), "refresh", refreshExpiredDate);
////        String decodeToken = jwtTokenHelper.decodeToken(token);
//
//                DataTokenResponse dataTokenResponse = new DataTokenResponse();
//                dataTokenResponse.setToken(token);
//                dataTokenResponse.setRefreshToken(refreshToken);
//                DataResponse dataResponse = new DataResponse();
//                dataResponse.setData(HttpStatus.OK.value());
//                dataResponse.setSuccess(true);
//                dataResponse.setDesc("");
//                dataResponse.setData(dataTokenResponse);
//                System.out.println("Thành công api");
//                response.setHeader("Authorization", "Bearer " + token);
//                return new ResponseEntity<>(dataResponse, HttpStatus.OK);
//    }
//}
