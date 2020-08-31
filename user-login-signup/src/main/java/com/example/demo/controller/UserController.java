package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.UserRequestLoginModel;
import com.example.demo.models.UserRequestSignUpModel;
import com.example.demo.models.UserResponseModel;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserResponseModel> userSignUp(@RequestBody UserRequestSignUpModel userRequestSignUpModel){
		UserResponseModel userResponseModel = userService.userSignUp(userRequestSignUpModel);
		if(userResponseModel.getConfirmed() == "UserName Already Exists") {
			return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.CREATED);
	}

//	@PostMapping("/login")
//	public ResponseEntity<UserResponseModel> userLogin(@RequestBody UserRequestLoginModel userRequestLoginModel){
//		UserResponseModel userResponseModel = userService.userLogin(userRequestLoginModel);
//		if(userResponseModel.getConfirmed() == "Please Sign Up") {
//			return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.NOT_FOUND);
//		}
//		else if(userResponseModel.getConfirmed() == "Email Id Confirmation Required") {
//			return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.UNAUTHORIZED);
//		}
//		return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.OK);
//	}
	
	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@RequestBody UserRequestLoginModel userRequestLoginModel){
		String confirmation = userService.userLogin(userRequestLoginModel);
		if(confirmation == "Please Sign Up") {
			return new ResponseEntity<String>(confirmation, HttpStatus.NOT_FOUND);
		}
		else if(confirmation == "Email Id Confirmation Required") {
			return new ResponseEntity<String>(confirmation, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>(confirmation, HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(){
		return new ResponseEntity<String>(userService.logout(), HttpStatus.OK); 
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserResponseModel> updateProfile(@PathVariable Long id, @RequestBody UserRequestSignUpModel userRequestSignUpModel){
		UserResponseModel userResponseModel = userService.updateProfile(id, userRequestSignUpModel);
		return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.OK);
	}
	
	@PostMapping("/activate")
	public ResponseEntity<String> activateAccount(@RequestBody UserRequestLoginModel userRequestLoginModel){
		return new ResponseEntity<String>(userService.activateAccount(userRequestLoginModel), HttpStatus.OK);
	}
	
	@GetMapping("/profile/{userName}")
	public ResponseEntity<UserResponseModel> getUserProfile(@PathVariable String userName){
		return new ResponseEntity<UserResponseModel>(userService.getUserProfile(userName), HttpStatus.OK);
	}
			

}
