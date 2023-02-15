package com.example.demo.interfaces;

import com.example.demo.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginInterface extends JpaRepository<LoginEntity, Integer> {
	LoginEntity findByUserName(String userName);
}
