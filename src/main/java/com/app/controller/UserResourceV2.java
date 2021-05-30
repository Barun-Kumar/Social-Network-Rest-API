package com.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.exception.UserNotFoundException;
import com.app.model.Post;
import com.app.model.User;
import com.app.service.PostService;
import com.app.service.UserServiceV2;

@RestController
@RequestMapping("/v2/users")
public class UserResourceV2 {
	@Autowired
	UserServiceV2 userService;
	@Autowired
	PostService postService;

	@GetMapping
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@GetMapping(value = "/{userId}")
	private Optional<User> getUserById(@PathVariable Integer userId) {
		return userService.getUser(userId);
	}

	@PostMapping
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		System.out.println("Save user :"+user);
		User savedUser = userService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(value = "/{userId}")
	private void deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
	}
	
	@GetMapping("/{userId}/posts")
	public List<Post> getAllPostOfUser(@PathVariable int userId){
		Optional<User> userOptional = userService.getUser(userId);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User is not found for id :"+userId);
		}
		return userOptional.get().getPost();
	}
	
	@PostMapping("/{userId}/posts")
	public  ResponseEntity<Object> createPostForUser(@PathVariable int userId, @RequestBody Post post) {
		Optional<User> userOptional = userService.getUser(userId);
		User user=userOptional.get();
		post.setUser(user);
		postService.createPost(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{userId}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}
