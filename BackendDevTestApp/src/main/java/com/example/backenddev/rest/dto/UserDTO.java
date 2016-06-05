package com.example.backenddev.rest.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.backenddev.model.Password;
import com.example.backenddev.model.Phone;
import com.example.backenddev.model.User;

public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Set<Phone> phones = new HashSet<>();

    public UserDTO()
    {
    }

    public UserDTO(User user, Password password)
    {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phones = user.getPhones();
        this.password = password.getPassword();
    }


    public User createUser()
    {
        User user = new User();
        user.setEmail(this.email);
        user.setPhones(this.phones);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setActivated(false);
        return user;
    }

    public Password createPassword()
    {
        Password password = new Password();
        password.setPassword(this.password);
        return password;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}
    
}
