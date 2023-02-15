package com.example.demo.interfaces;

import com.example.demo.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoginInterface extends JpaRepository<LoginEntity, Integer> {
	List<LoginEntity> findByUserName(String userName);

	@Query(value = "select * from login_credentials s where s.userName=:userName", nativeQuery = true)
	LoginEntity getByUserName(String userName);
}
