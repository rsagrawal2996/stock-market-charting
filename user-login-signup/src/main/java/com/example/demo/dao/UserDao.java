package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	public User findByUserNameAndPassword(String userName, String password);
	public User findByUserName(String userName);

}
