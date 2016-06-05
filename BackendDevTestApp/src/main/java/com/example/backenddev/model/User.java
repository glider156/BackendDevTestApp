package com.example.backenddev.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@XmlRootElement
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(nullable = false)
    @NotNull
    @Pattern(regexp = "^[^\\s]+@[^\\.][^\\s]+$", message = "{user.email.pattern}")
    private String email;

    @Column(nullable = false, name = "first_name")
    @NotNull
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "{user.name.pattern}")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    @NotNull
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "{user.name.pattern}")
    private String lastName;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_id")
    @Size(min = 1, max = 5)
    @Valid
    private Set<Phone> phones = new HashSet<>();
    
    @Column(nullable = false, name = "is_activated")
    private boolean isActivated;
    
	public User() {
		super();
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


	public Set<Phone> getPhones() {
		return phones;
	}


	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}


	public boolean isActivated() {
		return isActivated;
	}


	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}


	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() 
	{
        return id != null ? id.hashCode() :
            Objects.hash(email, firstName, lastName, isActivated, phones);
	}


	@Override
	public boolean equals(Object o) 
	{
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        User user = (User) o;
        return id != null && user.id != null ? Objects.equals(id, user.id) :
                        Objects.equals(email, user.email) &&
                        Objects.equals(firstName, user.firstName) &&
                        Objects.equals(isActivated, user.isActivated) &&
                        Objects.equals(lastName, user.lastName) &&
                        Objects.equals(phones, user.phones);
	}


}


