package com.example.backenddev.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.example.backenddev.model.Password;
import com.example.backenddev.model.User;

public interface RegistrationService {
	void registerUser(User user, Password password) throws AddressException, MessagingException;
    void activate(User user) throws AddressException, MessagingException;
	void activateByCode(String code) throws AddressException, MessagingException;
}
