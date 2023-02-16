package com.example.demo.repository;

import com.example.demo.entity.LoginEntity;
import com.example.demo.interfaces.LoginInterface;
import com.example.demo.response.LoginResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {

    @Autowired
    private LoginInterface loginInterface;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginResponse saveUserNamePassword(LoginEntity loginEntity) {
        LoginResponse loginResponse = new LoginResponse();
        if (loginEntity != null) {
            loginEntity.setUserPassword(bCryptPasswordEncoder.encode(loginEntity.getUserPassword()));
            loginEntity.setDate(Utility.getDate());
            LoginEntity loginEntity1 = loginInterface.save(loginEntity);
            loginResponse.setStatus(200);
            loginResponse.setMessage("Details have been Saved Successfully");
            loginResponse.setLoginEntity(loginEntity1);
            return loginResponse;
        }
        loginResponse.setStatus(500);
        loginResponse.setMessage("Not able to save Record , Something went wrong");
        loginResponse.setLoginEntity(null);
        return loginResponse;
    }

    public LoginResponse getUserNamePassword(Integer Id) {
        LoginResponse loginResponse = new LoginResponse();
        Optional<LoginEntity> loginEntity = loginInterface.findById(Id);
        if (loginEntity.isPresent()) {
            loginResponse.setStatus(200);
            loginResponse.setMessage("Details are found for the Id" + " " + Id);
            loginResponse.setLoginEntity(loginEntity.get());
        } else {
            loginResponse.setStatus(500);
            loginResponse.setMessage("Not able to get Record ,Something went wrong");
            loginResponse.setLoginEntity(null);
        }
        return loginResponse;
    }
    //To-Do:- Write logic for not having duplicate userName.

    public LoginResponse updateUserNamePassword(LoginEntity loginEntity) {
        LoginResponse loginResponse = new LoginResponse();
        List<LoginEntity> loginEntityList = loginInterface.findByUserName(loginEntity.getUserName());
        if (loginEntityList != null && !DuplicateUserPasswordFindingCode(loginEntity, loginEntityList)) {
            loginEntity.setDate(Utility.getDate());
            loginResponse.setStatus(200);
            loginResponse.setMessage("Details has been updated for the Id" + " " + loginEntity.getId());
            loginInterface.save(loginEntity);
            loginResponse.setLoginEntity(loginEntity);
            loginResponse.setLoginEntityList(loginEntityList);
        } else {
            loginResponse.setStatus(500);
            loginResponse.setMessage("Previous Used Password is not allowed");
            loginEntity.setDate(Utility.getDate());
        }
        return loginResponse;
    }

    private static Boolean DuplicateUserPasswordFindingCode(LoginEntity loginEntity, List<LoginEntity> loginEntityList) {
        for (LoginEntity login : loginEntityList) {
            if (login.getUserPassword().equals(loginEntity.getUserPassword())) {
                return true;
            }
        }
        return false;
    }

    public LoginResponse deleteUserNamePassword(Integer Id) {
        LoginResponse loginResponse = new LoginResponse();
        Optional<LoginEntity> loginEntity = loginInterface.findById(Id);
        if (!loginEntity.isPresent()) {
            loginResponse.setStatus(500);
            loginResponse.setMessage("Unable to find Details for the ID" + " " + Id);
            loginResponse.setLoginEntity(null);
        } else {
            loginInterface.deleteById(Id);
            loginResponse.setStatus(200);
            loginResponse.setMessage("Record has been deleted for the ID" + " " + Id);
            loginResponse.setLoginEntity(loginEntity.get());
        }
        return loginResponse;
    }

    public LoginResponse findByUserName(String userName) {
        LoginResponse loginResponse = new LoginResponse();
        List<LoginEntity> loginEntityList = loginInterface.findByUserName(userName);
        if (loginEntityList != null) {
            loginResponse.setStatus(200);
            loginResponse.setMessage("Details are found for the Id" + " " + userName);
            loginResponse.setLoginEntityList(loginEntityList);
        } else {
            loginResponse.setStatus(500);
            loginResponse.setMessage("Not able to get Record ,Something went wrong");
            loginResponse.setLoginEntity(null);
        }
        return loginResponse;
    }

    public LoginResponse authenticateByUserName(String userName, String userPassword) {
        LoginResponse loginResponse = new LoginResponse();
        LoginEntity loginEntityList = loginInterface.getByUserName(userName);
        if (loginEntityList != null) {
            if (bCryptPasswordEncoder.matches(userPassword, loginEntityList.getUserPassword())) {
                loginResponse.setStatus(200);
                loginResponse.setMessage("Password Matched Authentication Successful");
                loginResponse.setLoginEntity(loginEntityList);
            } else {
                loginResponse.setStatus(500);
                loginResponse.setMessage("Password Matched Authentication failed");
                loginResponse.setLoginEntity(null);
            }
        }
        return loginResponse;
    }
}
