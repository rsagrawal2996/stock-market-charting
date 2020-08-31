package com.example.demo.service;

import java.util.List;

import com.example.demo.models.UserRequestLoginModel;
import com.example.demo.models.UserRequestSignUpModel;
import com.example.demo.models.UserResponseModel;

public interface UserService {
	public UserResponseModel userSignUp(UserRequestSignUpModel userRequestSignUpModel);
	//public UserResponseModel userLogin(UserRequestLoginModel userRequestLoginModel);
	public String userLogin(UserRequestLoginModel userRequestLoginModel);
	public String logout();	
	//public List<UserResponseModel> getAllUsers();
	public UserResponseModel getUserProfile(String userName);
	public UserResponseModel updateProfile(Long id, UserRequestSignUpModel userRequestSignUpModel);
	//public UserResponseModel activateAccount(UserRequestLoginModel userRequestLoginModel);
	public String activateAccount(UserRequestLoginModel userRequestLoginModel);

}
