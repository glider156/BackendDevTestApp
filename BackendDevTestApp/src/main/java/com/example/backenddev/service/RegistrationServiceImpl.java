package com.example.backenddev.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.example.backenddev.model.ActivationCode;
import com.example.backenddev.model.Password;
import com.example.backenddev.model.User;

@Stateless
public class RegistrationServiceImpl implements RegistrationService {

	@Inject
	private UserService userService; 
	
	@Inject
	private PasswordService passwordService;
	
	@Inject
	private ActivationCodeService activationCodeService;
	
	@Inject
	private EmailService emailService;
	
	@Override
	public void registerUser(User user, Password password) throws AddressException, MessagingException 
	{
		userService.create(user);
		
		password.setUser(user);
		passwordService.create(password);

		ActivationCode activationCode = activationCodeService.generateNew();
		activationCode.setUser(user);
		activationCodeService.create(activationCode);
		emailService.sendActivationRequest(activationCode);
	}

	@Override
	public void activate(User user) throws AddressException, MessagingException 
	{
		user.setActivated(true);
		userService.update(user);
		emailService.sendActivationConfirmation(user);
	}
	
	@Override
	public void activateByCode(String code) throws AddressException, MessagingException
	{
		ActivationCode activationCode = activationCodeService.findByCode(code);
		activate(activationCode.getUser());
		activationCodeService.delete(activationCode);
	}
	
	
	

}
