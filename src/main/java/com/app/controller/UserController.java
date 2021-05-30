package com.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.exception.UserNotFoundException;
import com.app.model.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired(required = true)
	UserService userService;
	@Autowired
	MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers() {
		System.out.println("Users is called");
		return userService.getUsers();
	}

	@GetMapping(path = "/{userId}")
	public EntityModel<User> getUserById(@PathVariable int userId) {
		System.out.println("get user by id");
		User user = userService.getUser(userId);
		if (user == null) {
			throw new UserNotFoundException("Id:" + userId);
		}
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());
		model.add(link.withRel("all-users"));
		return model;
	}

	@PostMapping
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		System.out.println("Save user");
		User savedUser = userService.saveUser(user.getName());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{userId}")
	public User deleteUser(@PathVariable int userId) {
		System.out.println("Deleting user");
		User user = userService.deleteUser(userId);
		if (user == null) {
			throw new UserNotFoundException("Id :" + userId);
		}
		return user;
	}

	@GetMapping("/hello")
	public String helloWorld(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		// en ="Hellow World"
		// nl ="Goede Morgen"
		// fr ="Bonjour"

		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
}
