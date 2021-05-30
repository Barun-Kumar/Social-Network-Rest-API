package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserDao;

@Service
public class UserService {
	
	@Autowired(required=true)
	UserDao userDao;
	
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	public User getUser(String userName) {
		return userDao.getUser(userName);
	}

	public User getUser(int userId) {
		return userDao.getUser(userId);
	}
	
	public User saveUser(String userName) {
		return userDao.saveUser(userName);
	}

	public User deleteUser(int userId) {
		return userDao.deleteUser(userId);
		
	}
}
