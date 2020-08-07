package com.darkroom.main.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darkroom.main.entity.User;
import com.darkroom.main.exception.CustomException;
import com.darkroom.main.exception.ErrorResponse;
import com.darkroom.main.service.UserService;



@RequestMapping("/api")
@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/get")
	public List<User> get() {
		return userService.getUser();
	}
	
	@GetMapping("/getUsername/{username}")
	public User getId(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}
	 
	 @PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) 
	throws CustomException {
		
		String username = user.getUsername();
		String password = user.getPassword();
		
		User u = userService.login(username, password);
		if(u == null) {
			throw new CustomException("The username/password specified is not valid.");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
		.body(userService.login(username, password));
	}
	

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody User user, 
	BindingResult result) throws CustomException {
		
		if(result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream()
			.map(e -> e.getDefaultMessage()).collect(Collectors.toList());
			
			return ResponseEntity.badRequest()
			.body(new ErrorResponse("400", "Validation failure", errors));
		}
		
		User u = userService.getUserByUsername(user.getUsername());
		if(u != null) {
				return ResponseEntity.badRequest()
				.body(new ErrorResponse("400", "Validation failure", "The username " 
				+ u.getUsername() + " already exists. Please use a different username."));
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(user));
	}
}
