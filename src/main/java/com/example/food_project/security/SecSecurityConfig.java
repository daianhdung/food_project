package com.example.food_project.security;

//import com.example.food_project.jwt.JwtTokenFilter;
import com.example.food_project.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        System.out.println("test");
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
        System.out.println("Thành công");
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
//                .addFilter(new CustomAuthenticationFilter(authenticationManager()))
                .requestCache().requestCache(httpSessionRequestCache).and()
//                .authenticationProvider(customAuthenticationProvider).exceptionHandling().and()
                .authorizeRequests()
                .antMatchers("/font/**", "/img/**", "/css/**", "/js/**", "/vendor/**", "/signin",
                        "/api/signin", "/api/file/**", "/api/refresh-token", "/reset-password").permitAll()
                .antMatchers("/home", "/", "/signup").permitAll()
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

