package com.projectmanager.pm.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;



@Entity
@Table(name = "users")
public class User {

	
	// Fields
		
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @NaturalId
    private String username;
    
    @NotNull
    @Size(min=8, message="The minimun character allow for password are 8")
    private String password;
    

    // One to one User - Developer relationship

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, optional = false)
    private Developer developer;
    
    
    
	// Getters and Setters
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Developer getDeveloper() {
		return developer;
	}


	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	
	
    
}
