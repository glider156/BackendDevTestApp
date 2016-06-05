package com.example.backenddev.service;

import java.util.List;

import com.example.backenddev.model.Password;
import com.example.backenddev.model.User;

public interface PasswordService {
    void create(Password password);
    void setNewPassword(User user, String newPassword);
	void resetPassword(User user, String oldPassword, String newPassword);
	public List<Password> getAll();
}
