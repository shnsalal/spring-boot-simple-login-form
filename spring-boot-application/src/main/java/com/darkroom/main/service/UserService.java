package com.darkroom.main.service;

import java.util.List;

import com.darkroom.main.entity.User;


public interface UserService {
	public List<User> getUser();
	public User login(String username, String password);
	public User signup(User user);
	public User getUserByUsername(String username);
}
