package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserRepository;

@Service
public class UserServiceV2 {
	
	@Autowired(required=true)
	UserRepository userRepository;
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User getUser(String userName) {
		return null;
	}

	public Optional<User> getUser(int userId) {
		return userRepository.findById(userId);
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(int userId) {
		 userRepository.deleteById(userId);
		
	}
}
