package com.example.demo.controller;

import com.example.demo.entity.LoginEntity;
import com.example.demo.response.LoginResponse;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.POST, value = "/saveUserNamePassword",  produces = { "application/xml" })
    public LoginResponse saveUserNamePassword(@RequestBody LoginEntity loginEntity) {
        return loginService.saveUserNamePassword(loginEntity);
    }

    @GetMapping("/getUserNamePassword/{Id}")
    public LoginResponse getUserNamePassword(@PathVariable Integer Id) {
        return loginService.getUserNamePassword(Id);
    }

    @PutMapping("/updateUserNamePassword")
    public LoginResponse updateUserNamePassword(@RequestBody LoginEntity loginEntity) {
        return loginService.updateUserNamePassword(loginEntity);
    }

    @DeleteMapping("/deleteUserNamePassword/{Id}")
    public LoginResponse deleteUserNamePassword(@PathVariable Integer Id) {
        return loginService.deleteUserNamePassword(Id);
    }

    @GetMapping("/findByUserName/{userName}")
    public LoginResponse findByUserName(@PathVariable String userName) {
        return loginService.findByUserName(userName);
    }

    @GetMapping("/authenticateByUserName/{userName}/{userPassword}")
    public LoginResponse authenticateByUserName(@PathVariable String userName, @PathVariable String userPassword) {
        return loginService.authenticateByUserName(userName, userPassword);
    }
}
