package com.example.demo.service;

//import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDao;
import com.example.demo.models.User;
import com.example.demo.models.UserRequestLoginModel;
import com.example.demo.models.UserRequestSignUpModel;
import com.example.demo.models.UserResponseModel;

@Service
public class UserServiceImpl implements UserService{
	private UserDao userDao;
	private ModelMapper modelMapper;
	

	public UserServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
		modelMapper = new ModelMapper();
	}

	@Override
	@Transactional
	public UserResponseModel userSignUp(UserRequestSignUpModel userRequestSignUpModel) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User user = modelMapper.map(userRequestSignUpModel,User.class);
		user.setUserType("user");
		User usernew = userDao.findByUserName(userRequestSignUpModel.getUserName());
		if(usernew == null) {
			user.setConfirmed("Email Id Confirmation Required");			
	        userDao.saveAndFlush(user);	        
		}
		else {
			user.setConfirmed("UserName Already Exists");			
		}
		return modelMapper.map(user,UserResponseModel.class);
		
	}

//	@Override
//	@Transactional
//	public UserResponseModel userLogin(UserRequestLoginModel userRequestLoginModel) {
//		// TODO Auto-generated method stub
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		// user = modelMapper.map(userRequestLoginModel,User.class);
//		User user = userDao.findByUserNameAndPassword(userRequestLoginModel.getUserName(), userRequestLoginModel.getPassword());
//		if (user != null) {
//			if(user.getConfirmed() == "Email Id Confirmed") {
//				return modelMapper.map(user,UserResponseModel.class);					        	        
//			}
//			else {
//				UserResponseModel userResponse = modelMapper.map(userRequestLoginModel, UserResponseModel.class);
//				userResponse.setConfirmed(user.getConfirmed());
//				return userResponse;
//			}			
//		}
//		
//		else {
//			UserResponseModel userResponse = modelMapper.map(userRequestLoginModel, UserResponseModel.class);
//			userResponse.setConfirmed("Please Sign Up");	
//			return userResponse;
//		}
//		
//	}
	
	@Override
	@Transactional
	public String userLogin(UserRequestLoginModel userRequestLoginModel) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		// user = modelMapper.map(userRequestLoginModel,User.class);
		User user = userDao.findByUserNameAndPassword(userRequestLoginModel.getUserName(), userRequestLoginModel.getPassword());
		if (user != null) {
			if(user.getConfirmed() == "Email Id Confirmed") {
				return "Successfully Logged In";					        	        
			}
			else {
				//UserResponseModel userResponse = modelMapper.map(userRequestLoginModel, UserResponseModel.class);
				//userResponse.setConfirmed(user.getConfirmed());
				return "Email Id Confirmation Required";
			}			
		}
		
		else {
			//UserResponseModel userResponse = modelMapper.map(userRequestLoginModel, UserResponseModel.class);
			//userResponse.setConfirmed("Please Sign Up");	
			return "Please Sign Up";
		}
		
	}

	@Override
	@Transactional
	public String logout() {
		// TODO Auto-generated method stub
		return "Successfully Logged Out";
	}

//	@Override
//	@Transactional
//	public UserResponseModel findByUserName(String userName) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	@Transactional
	public UserResponseModel updateProfile(Long id, UserRequestSignUpModel userRequestSignUpModel) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User user = userDao.findById(id).get();		
		User usernew = modelMapper.map(userRequestSignUpModel, User.class);
		usernew.setId(user.getId());
		usernew.setConfirmed(user.getConfirmed());
		usernew.setUserType(user.getUserType());
		userDao.saveAndFlush(usernew);		
		return modelMapper.map(usernew,UserResponseModel.class);
		
	}

//	@Override
//	public UserResponseModel activateAccount(UserRequestLoginModel userRequestLoginModel) {
//		// TODO Auto-generated method stub
//		User user = userDao.findByUserNameAndPassword(userRequestLoginModel.getUserName(), userRequestLoginModel.getPassword());
//		user.setConfirmed("Email Id Confirmed");
//		userDao.saveAndFlush(user);
//		return modelMapper.map(user,UserResponseModel.class);
//	}
	
	@Override
	@Transactional
	public String activateAccount(UserRequestLoginModel userRequestLoginModel) {
		// TODO Auto-generated method stub
		User user = userDao.findByUserNameAndPassword(userRequestLoginModel.getUserName(), userRequestLoginModel.getPassword());
		user.setConfirmed("Email Id Confirmed");
		userDao.saveAndFlush(user);
		return "Email Id Confirmed";
	}

	@Override
	@Transactional
	public UserResponseModel getUserProfile(String userName) {
		// TODO Auto-generated method stub
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User user = userDao.findByUserName(userName);			
		return modelMapper.map(user, UserResponseModel.class);	
		
	}
	
	
}
