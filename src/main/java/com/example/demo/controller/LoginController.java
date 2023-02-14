package com.example.demo.controller;

import com.example.demo.entity.LoginEntity;
import com.example.demo.response.LoginResponse;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/saveUserNamePassword")
    public LoginResponse saveUserNamePassword(@RequestBody LoginEntity loginEntity){
        return loginService.saveUserNamePassword(loginEntity);
    }
}
