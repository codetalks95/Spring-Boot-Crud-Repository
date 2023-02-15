package com.example.demo.repository;

import com.example.demo.entity.LoginEntity;
import com.example.demo.interfaces.LoginInterface;
import com.example.demo.response.LoginResponse;

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


	//To-Do- Correct the format of Messages

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
		loginResponse.setMessage("Something went wrong");
		loginResponse.setLoginEntity(null);
		return loginResponse;
	}

	public LoginResponse getUserNamePassword(Integer Id) {
		LoginResponse loginResponse = new LoginResponse();
		Optional<LoginEntity> loginEntity = loginInterface.findById(Id);
		if (loginEntity.isPresent()) {
			loginResponse.setStatus(200);
			loginResponse.setMessage("able to get details");
			loginResponse.setLoginEntity(loginEntity.get());
		} else {
			loginResponse.setStatus(500);
			loginResponse.setMessage("Something went wrong");
			loginResponse.setLoginEntity(null);
		}
		return loginResponse;
	}

	public LoginResponse updateUserNamePassword(LoginEntity loginEntity) {
		LoginResponse loginResponse = new LoginResponse();
		Optional<LoginEntity> log= loginInterface.findById(loginEntity.getId());
		if (log.isPresent()) {
			log.get().setUserName(loginEntity.getUserName());
			log.get().setUserPassword(loginEntity.getUserPassword());
			log.get().setDate(Utility.getDate());
			LoginEntity login = loginInterface.save(loginEntity);
			loginResponse.setStatus(200);
			loginResponse.setMessage("updated");
			loginResponse.setLoginEntity(login);
		} else {
			loginResponse.setStatus(500);
			loginResponse.setMessage("unable to update the data ");
			loginResponse.setLoginEntity(null);
		}
		return loginResponse;
	}
	public LoginResponse deleteUserNamePassword(Integer Id) {
		LoginResponse loginResponse = new LoginResponse();
		Optional<LoginEntity> loginEntity= loginInterface.findById(Id);
		if(!loginEntity.isPresent()) {
			loginResponse.setStatus(500);
			loginResponse.setMessage("unable to find id to delete data ");
			loginResponse.setLoginEntity(null);
		}else {
			loginInterface.deleteById(Id);;
			loginResponse.setStatus(200);
			loginResponse.setMessage("deleted");
			loginResponse.setLoginEntity(loginEntity.get());
		}
		return loginResponse;

	}
}
