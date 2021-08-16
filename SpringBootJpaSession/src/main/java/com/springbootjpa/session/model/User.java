package com.springbootjpa.session.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Nagu
 *
 */
@Entity
@Table(name="Users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class User implements java.io.Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
	
	@Column(name = "email_address")
    @NotEmpty(message="Provide email id")
    @Email
    private String emailAddress;
	
    @Column(name = "password") 
    @JsonIgnore // Ignore the password with JSON requests (security related)
    private String password;
    
    
    @NotEmpty(message="Provide User Name") // Value is mandatory
    @Size(min = 2, max = 50) // Value must contain at least 2 characters and a maximum of 50 characters
    @Pattern(regexp = "[a-zA-Z]+") // Value must consist of values as per Regular Expression pattern
    @Column(name = "name") 
    private String name;
    
    
    
    @NotNull(message = "Please enter id")// Value is mandatory
    @Digits(integer = 2, fraction = 0) // Value must consist of digits only with a maximum of 2 digits
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 60, message = "Age should not be greater than 60")
    @Column(name = "age") 
    private int age;
    
    
    @Column(name = "created_by")
    @NotEmpty(message="Provide Created By")
    private String createdBy;
    
    @Column(name = "address")
    @NotEmpty(message="Provide User Address")
    @Size(min = 10, max = 200, message 
    = "Address must be between 10 and 200 characters")
    private String userAddress;
    
    
    @Column(name = "created_on") 
    private Date createdOn;

    public User()
    {
    	
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
}