package com.darkroom.main.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darkroom.main.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	public User findByUsernameAndPassword(String username, String password);
	public User findByUsername(String username);
}
