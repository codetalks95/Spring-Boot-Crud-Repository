package com.example.demo.repository;

import com.example.demo.entity.LoginEntity;
import com.example.demo.interfaces.LoginInterface;
import com.example.demo.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {

    @Autowired
    private LoginInterface loginInterface;

    public LoginResponse saveUserNamePassword(LoginEntity loginEntity) {
        LoginResponse loginResponse = new LoginResponse();
        if (loginEntity != null) {
            LoginEntity loginEntity1 = loginInterface.save(loginEntity);
            loginResponse.setStatus(200);
            loginResponse.setMessage("Details have been Saved Successfully");
            loginResponse.setLoginEntity(loginEntity1);
            return loginResponse;
        }
        loginResponse.setStatus(500);
        loginResponse.setMessage("Something went wrong");
        loginResponse.setLoginEntity(null);
        return loginResponse;
    }
}
