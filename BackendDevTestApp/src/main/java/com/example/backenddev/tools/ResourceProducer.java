package com.example.backenddev.tools;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class ResourceProducer {

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) 
    {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
    
    @Produces
    public SecureRandom produceSecureRandom(InjectionPoint injectionPoint) throws NoSuchAlgorithmException 
    {
		return SecureRandom.getInstanceStrong();
    	
    }
    
    @Produces
    public ResourceBundle produceResourceBundle(InjectionPoint injectionPoint)
    {
    	Locale currentLocale = Locale.getDefault();
    	return  ResourceBundle.getBundle("MessagesBundle", currentLocale);
    	
    }
}
