package com.example.backenddev.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Phone
 *
 */
@Entity
@XmlRootElement
@Table(name = "phones")
public class Phone implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 3, max = 20, message="{phone.number.size}")
    @Digits(fraction = 0, integer = 20, message="{Only digits are allowed}")
    private String number;    

    
    @Column(nullable = false, name = "phone_type")
    @NotNull(message="{phone.phoneType.notnull}")
    @Enumerated(EnumType.ORDINAL)
    private PhoneType phoneType;
	
	public Phone() {
		super();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneType getProvider() {
		return phoneType;
	}

	public void setProvider(PhoneType phoneType) {
		this.phoneType = phoneType;
	}
	
	@Override
	public int hashCode() {
        return id != null ? id.hashCode() : Objects.hash(id, number, phoneType);
	}

	@Override
	public boolean equals(Object o) {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Phone that = (Phone) o;
        return id != null && that.id != null ? Objects.equals(id, that.id) :
                Objects.equals(number, that.number) &&
                Objects.equals(phoneType, that.phoneType);
	}
}
