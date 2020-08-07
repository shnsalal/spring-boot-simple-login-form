package com.darkroom.main.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darkroom.main.entity.User;
import com.darkroom.main.repository.UserRepository;
import com.darkroom.main.service.UserService;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getUser() {
		return userRepository.findAll();
	}

	@Override
	public User login(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		return user;
	}

	@Override
	public User signup(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
}