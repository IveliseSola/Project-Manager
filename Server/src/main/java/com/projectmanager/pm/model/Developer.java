package com.projectmanager.pm.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "developers")
public class Developer{
	
	@Id
	 private Long id;

	@NotNull
	 @Size(max = 100)
	 private String firstName;
	 
	 @NotNull
	 @Size(max = 100)
	 private String lastName;
	 
	 @NotNull
	 @Size(max = 50)
	 private String email;
	 
	 @NotNull
	 @Size(max = 100)
	 private String address;
	 
	 @NotNull
	 @Size(max = 12)
	 private String phone;
	 
	@NotNull
    private int yearsExperience;
	
	@ElementCollection
    private List<String> languages = new ArrayList<>(); 
	
	 
	 // Many to many Developer - Project relationship
	 
	
	  @ManyToMany(fetch = FetchType.LAZY,
	              cascade = { CascadeType.PERSIST, CascadeType.MERGE},
	              mappedBy = "developers")
	  
	    private Set<Project> projects = new HashSet<>();
	 
	    
	// One to many Developer - User relationship    
	    
	    @OneToOne(fetch = FetchType.LAZY)
	    @MapsId
	    private User user;
	    
	    
	 // One to Many Developer - Task relationship
		 
		@OneToMany( mappedBy = "developer", 
			        cascade = CascadeType.PERSIST, 
			        orphanRemoval = true )
	     private Set<Task> tasks = new HashSet<>();
    
		
	//Constructor
	   public Developer() {}
		
	    public Developer(long id, String firstName, String lastName, String email, String address, String phone,
	    		         int yearsExperience, List<String> languages, Set<Project> projects, User user, Set<Task> tasks ) {
	    	this.id = id;
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    	this.email = email;
	    	this.address = address;
	    	this.phone = phone;
	    	this.yearsExperience = yearsExperience;
	    	this.languages = languages;
	    	this.projects = projects;
	    	this.user = user;
	    	this.tasks = tasks;
	    }
		
	    
		
	// Getters and Setters
	 

	public List<String> getLanguages() {
		return languages;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public int getYearExperience() {
		return yearsExperience;
	}

	public void setYearExperience(int yearExperience) {
		this.yearsExperience = yearExperience;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public int getYearsExperience() {
		return yearsExperience;
	}

	public void setYearsExperience(int yearsExperience) {
		this.yearsExperience = yearsExperience;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	
}

