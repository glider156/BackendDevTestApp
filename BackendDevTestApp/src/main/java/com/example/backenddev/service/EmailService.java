package com.example.backenddev.service;

import javax.mail.MessagingException;

import com.example.backenddev.model.ActivationCode;
import com.example.backenddev.model.User;

public interface EmailService {

	void sendActivationRequest(ActivationCode activationCode) throws MessagingException;
	void sendActivationConfirmation(User user) throws MessagingException;
}
