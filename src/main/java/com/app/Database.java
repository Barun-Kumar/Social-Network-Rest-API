package com.app;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.app.model.User;
@Repository
public class Database {
	
	public static Map<Integer,User> users =null;
	
	static {
		users = new HashMap<Integer, User>();
		users.put(1, new User(1, "Varun", new Date(), null));
		users.put(2, new User(2, "Rahul", new Date(), null));
		users.put(3, new User(3, "Deeksha", new Date(), null));
		users.put(4, new User(4, "John", new Date(), null));
		users.put(5, new User(5, "Tom", new Date(), null));
		users.put(6, new User(6, "Sam", new Date(), null));
	}
	
	public static List<User>getAllUser() {
		return users.values().stream().collect(Collectors.toList());
	}
	
	public static User getUserById(int userId) {
		return users.get(userId);
	}
	
	public User saveUser(String name) {
		System.out.println("Saving user");
		int id =users.size()+1;
		User user = new User(id,name,new Date(), null);
		users.put(id, user);
		return user;
		
	}

	public static User deleteUser(int userId) {
		User user=users.remove(userId);
		return user;
	}

}
