package com.app.repository;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.Database;
import com.app.model.Post;
import com.app.model.User;

@Repository
public class UserDao {
	
	@Autowired
	Database database;

	public List<User> getUsers() {
		return Database.getAllUser();
	}

	public User getUser(String userName) {
		User user= new User();
		user.setName(userName);
		user.setDob(new Date());
		user.setId(1);
		return user;
	}

	public User getUser(int userId) {
		return Database.getUserById(userId);
	}
	
	public User saveUser(String userName) {
		return database.saveUser(userName);
	}

	public User deleteUser(int userId) {
		return Database.deleteUser(userId);
	}
}
