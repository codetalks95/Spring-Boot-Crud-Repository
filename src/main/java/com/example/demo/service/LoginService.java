package com.example.demo.service;

import com.example.demo.entity.LoginEntity;
import com.example.demo.repository.LoginRepository;
import com.example.demo.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public LoginResponse saveUserNamePassword(LoginEntity loginEntity) {
       return loginRepository.saveUserNamePassword(loginEntity);
    }
}