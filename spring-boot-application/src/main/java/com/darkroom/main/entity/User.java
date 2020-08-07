package com.darkroom.main.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;


@Entity
public class User {
	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;
	
	public UUID getId() {
		return id;
	}
	
	@NotNull
	@Email(message = "Please provide a valid email")
	@NotEmpty(message = "Please provide a valid email")
	@Size(min=5, message = "Username should be greater than 5")
	@Column(unique = true)
	private String username;
	
	@NotNull
	@Size(min=5, message = "Password should be greater than 5")
    @NotEmpty(message = "Please provide a valid email")
    private String password;
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
//	public User() {
//		
//	}
//	
//	public User(String username, String password) {
//			this.username = username;
//			this.password = password;
//	}
}
