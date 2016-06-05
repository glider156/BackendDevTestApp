package com.example.backenddev.service;

import java.util.List;

import com.example.backenddev.model.User;

public interface UserService extends EntityService<User> {

	public List<User> getAll();
	boolean isEmailAlreadyRegistered(String email);
	User findUserByEmail(String email);
	User findUserById(Long id);
}
