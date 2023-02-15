package com.example.demo.interfaces;

import com.example.demo.entity.LoginEntity;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginInterface extends JpaRepository<LoginEntity, Integer> {
	List<LoginEntity> findByUserName(String userName);
	List<LoginEntity> findByUserNamePassword(String userName, String userPassword);
}
