package com.example.backenddev.service;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.example.backenddev.model.ActivationCode;
import com.example.backenddev.model.User;

@Stateless
public class EmailServiceImpl implements EmailService {

	@Inject
	private Logger log;
	
	@Inject
	private ResourceBundle resourceBundle;
	
//	@Resource(mappedName = "java:jboss/mail/Gmail") 
	@Resource(mappedName = "java:jboss/mail/Default") 
	private Session mailSession;	
	
	
	private void sendMail(String subj, String body, String to) throws MessagingException
	{
		String messageFrom = resourceBundle.getString("email.activation.from");
		mailSession.setDebug(true);
		
		try
		{
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(messageFrom));
		    InternetAddress[] address = {new InternetAddress(to)};
		    message.setRecipients(Message.RecipientType.TO, address);
		    message.setSubject(subj);
		    message.setSentDate(new Date());
		    message.setText(body);
		    Transport.send(message);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Mail sending failed", e);
			throw e;
		}
	}
	
	@Override
	public void sendActivationRequest(ActivationCode activationCode) throws MessagingException 
	{
		String messageBody = String.format(resourceBundle.getString("email.activation.request"), activationCode.getCode());
		String messageSubj = resourceBundle.getString("email.activation.subj.request");
		String to = String.format("%s %s <%s>", activationCode.getUser().getFirstName(), activationCode.getUser().getLastName(), activationCode.getUser().getEmail());
		
		sendMail(messageSubj, messageBody, to);
	}

	@Override
	public void sendActivationConfirmation(User user) throws MessagingException 
	{
		String messageBody = resourceBundle.getString("email.activation.confirmation"); 
		String messageSubj = resourceBundle.getString("email.activation.subj.confirmation");
		String to = String.format("%s %s <%s>", user.getFirstName(), user.getLastName(), user.getEmail());

		sendMail(messageSubj, messageBody, to);
	}

}
