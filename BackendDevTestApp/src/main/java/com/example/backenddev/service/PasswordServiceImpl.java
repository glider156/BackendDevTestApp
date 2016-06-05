package com.example.backenddev.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.example.backenddev.data.PasswordRepository;
import com.example.backenddev.model.Password;
import com.example.backenddev.model.User;

@Stateless
public class PasswordServiceImpl implements PasswordService {

	@Inject
	private PasswordRepository passwordRepository;
	
	@Override
	public void create(Password password) {
		passwordRepository.create(password);
	}

	@Override
	public void setNewPassword(User user, String newPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetPassword(User user, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub

	}
	
	public List<Password> getAll()
	{
		return passwordRepository.findAll();
	}
	
}
