package com.example.food_project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import static com.example.food_project.constants.ViewConstant.*;

@Configuration
@EnableWebSecurity
@Component
public class SecSecurityConfig{
    @Autowired
    HttpSessionRequestCache httpSessionRequestCache;
    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf()
                .disable()
                .requestCache().requestCache(httpSessionRequestCache).and()
                .authorizeRequests()
                .antMatchers("/font/**", "/img/**", "/css/**", "/js/**", "/vendor/**", SIGNIN_VIEW,
                        "/api/signin", "/api/file/**", "/api/refresh-token", RESET_PASSWORD_VIEW, SIGNUP_VIEW, HOME_VIEW,
                        LISTING_VIEW + "/category/**", DETAIL_VIEW + "/restaurant/**", "/api/food/**", "/"
                        ,"/listing/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage(SIGNIN_VIEW).loginProcessingUrl(SIGNIN_VIEW)
                .defaultSuccessUrl(HOME_VIEW).failureUrl(SIGNIN_VIEW + "?error=true")
                .permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl(SIGNIN_VIEW)
                .invalidateHttpSession(true).clearAuthentication(true).permitAll()
                .and().exceptionHandling().accessDeniedPage(SIGNIN_VIEW);

        return http.build();
    }



}

