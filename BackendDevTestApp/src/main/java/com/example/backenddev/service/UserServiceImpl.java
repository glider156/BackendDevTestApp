package com.example.backenddev.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.backenddev.data.UserRepository;
import com.example.backenddev.model.User;

@Stateless
public class UserServiceImpl implements UserService {
    
	@Inject
    private UserRepository userRepository;
	
	@Override
	public List<User> getAll()
	{
		return userRepository.findAll();
	}
	
	@Override
	public void create(User user) {
        userRepository.create(user);
	}

	@Override
	public User getReference(Object id) {
		return userRepository.getReference(id);
	}

	@Override
	public User find(Object id) {
		return userRepository.find(id);
	}

	@Override
	public User update(User entity) {
		return userRepository.edit(entity);
	}

	@Override
	public void delete(User entity) {
		userRepository.remove(entity);
	}
	@Override
	public int count() {
		return userRepository.count();
	}

	@Override
	public boolean isEmailAlreadyRegistered(String email) {
		return findUserByEmail(email) != null;
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.findById(id);
	}
	
}
