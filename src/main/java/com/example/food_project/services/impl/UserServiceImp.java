package com.example.food_project.services.impl;

import com.example.food_project.entity.UserEntity;
import com.example.food_project.repository.UserRepository;
import com.example.food_project.services.UserService;
import com.example.food_project.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

import static net.bytebuddy.utility.RandomString.make;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    StringUtil stringUtil;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public UserEntity getUser(String email) {
        return userRepository.findByEmail(email).size() > 0 ? userRepository.findByEmail(email).get(0) : null;
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        userEntity.setEmail(stringUtil.parseEmail(userEntity.getEmail()));
        userEntity.setPassword(passwordEncoder.encode(stringUtil.removeWhiteSpaceBeginAndEnd(userEntity.getPassword())));
        userEntity.setFullname(stringUtil.parseName(userEntity.getFullname()));
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity resetPassword(String email) throws MessagingException, UnsupportedEncodingException {
        email = stringUtil.parseEmail(email);
        UserEntity user = getUser(email);
        var randomPass = make(13);
        var message = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message);
        helper.setFrom("fooddeliveryyyy@gmail.com", "Food Delivery Production");
        helper.setTo(email);
        helper.setSubject("Password Forgot");
        helper.setText("Your new password is:<br>" + "<h3>" + randomPass + "</h3>", true);
        mailSender.send(message);
        user.setPassword(passwordEncoder.encode(stringUtil.removeWhiteSpaceBeginAndEnd(randomPass)));
        return userRepository.save(user);
    }


}
