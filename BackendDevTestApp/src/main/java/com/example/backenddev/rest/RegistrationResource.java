package com.example.backenddev.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.example.backenddev.model.ActivationCode_;
import com.example.backenddev.model.Password;
import com.example.backenddev.model.User;
import com.example.backenddev.model.User_;
import com.example.backenddev.rest.dto.UserDTO;
import com.example.backenddev.service.ActivationCodeService;
import com.example.backenddev.service.PasswordService;
import com.example.backenddev.service.RegistrationService;
import com.example.backenddev.service.UserService;

@Path("/registration")
@RequestScoped
@Produces("application/json")
@Consumes("application/json")
public class RegistrationResource {

	@Inject
	private Logger log;
	
    @Inject
    private RegistrationService registrationService;

    @Inject
    private UserService userService;
    
    @Inject
    private ActivationCodeService activationCodeService;
    
    
    @Inject
    private Validator validator;

    @GET
    @Path("/all")
    public Response getAll()
    {
        return Response.ok(userService.getAll()).build();
    }

    @GET
    @Path("/activate/{code}")
    public Response activate(@PathParam("code") final String code)
    {
		Response.ResponseBuilder builder = null;
		try
		{
			registrationService.activateByCode(code);
			builder = Response.ok();
		}
        catch (MessagingException e)
        {
        	log.log(Level.WARNING, "Sending email failed", e);
        	
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "Cannot send email confirmation");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
		catch (Exception e)
		{
        	log.log(Level.WARNING, "Activation failed", e);
        	
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
    	
    	
        return builder.build();        
    }
    
	@POST
	public Response create(final UserDTO dto) {
		Response.ResponseBuilder builder = null;
        try 
        {
        	User user = dto.createUser();
            Password password = dto.createPassword();

            validateUser(user);
            validatePassword(password);
            
            registrationService.registerUser(user, password);

            Map<String, String> responseObj = new HashMap<>();
            responseObj.put(User_.id.getName(), user.getId().toString());
            responseObj.put(ActivationCode_.code.getName(), activationCodeService.findByUser(user).getCode());
            builder = Response.ok(responseObj);
        } 
        catch (MessagingException e)
        {
        	log.log(Level.WARNING, "Sending email failed", e);
        	
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "Cannot send email confirmation");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        catch (ConstraintViolationException ce) 
        {
            builder = createViolationResponse(ce.getConstraintViolations());
        } 
        catch (ValidationException e) 
        {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put(User_.email.getName(), "Email taken");
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } 
        catch (Exception e) 
        {
        	log.log(Level.WARNING, "Creating user failed", e);
        	
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();        
	}
	
	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) 
	{
		Response.ResponseBuilder builder = null;

		try
		{
			User user = userService.findUserById(id);
            builder = Response.ok(user);
		}
		catch (NoResultException e)
		{
			 builder = Response.status(Response.Status.NOT_FOUND);
		}

		return builder.build();
	}

    private void validateUser(User user) throws ConstraintViolationException, ValidationException {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) 
        {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }

        if (userService.isEmailAlreadyRegistered(user.getEmail())) 
        {
            throw new ValidationException("Unique Email Violation");
        }
    }
	
    private void validatePassword(Password password) throws ConstraintViolationException, ValidationException {
        Set<ConstraintViolation<Password>> violations = validator.validate(password);

        if (!violations.isEmpty()) 
        {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }
    }
	
    private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
        log.fine("Validation completed. violations found: " + violations.size());

        Map<String, String> responseObj = new HashMap<>();

        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }
	
}
