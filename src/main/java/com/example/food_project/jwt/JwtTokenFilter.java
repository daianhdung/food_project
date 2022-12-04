package com.example.food_project.jwt;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenHelper jwtTokenHelper;
    private Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromHeader(request);
        if(token != null){
            if(jwtTokenHelper.validaToken(token)){
                //Token hợp lệ
                String json = jwtTokenHelper.decodeToken(token);
                System.out.println("ko công 2");
                Map<String, Object> map = gson.fromJson(json, Map.class);
                System.out.println("Kiem tra" + json + " - " + map.get("type").toString());
                if(StringUtils.hasText(map.get("type").toString()) && !map.get("type").toString().equals("refresh")){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    securityContext.setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request){
        //Lấy giá trị token ở header có key là Authorization

        if(request.getHeader("Authorization") != null){
            String strToken = request.getHeader("Authorization") ;
            System.out.println("ko công ?");
            if(StringUtils.hasText(strToken) && strToken.startsWith("Bearer")){
                //Xử lí khi token hợp lệ
                //substring(): Dùng để cắt chuỗi
                String finalToken = strToken.substring(7);
                System.out.println("ko công 4");
                return finalToken;
            }else {
                return null;
            }
        }else {
            System.out.println("ko công 7");
            return null;
        }
    }
}
