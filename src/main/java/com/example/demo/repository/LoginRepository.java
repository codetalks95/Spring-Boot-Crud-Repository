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
        if (loginEntity != null || loginEntity.getPassword() != null || loginEntity.getUsername() != null) {
            LoginEntity loginEntity1 = loginInterface.save(loginEntity);
            if (loginEntity1 != null) {
                loginResponse.setStatus(200);
                loginResponse.setMessage("Details have been Saved Successfully");
                loginResponse.setLoginEntity(loginEntity);
                return loginResponse;
            }

        }
        loginResponse.setStatus(500);
        loginResponse.setMessage("Something went wrong");
        loginResponse.setLoginEntity(loginEntity);
        return loginResponse;
    }
}
