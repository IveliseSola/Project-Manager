package com.projectmanager.pm.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.User;



@Entity
@Table(name = "tasks")
public class Task extends Product{

    
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	
	private String developedBy;
	
    //Many to One Task - Project relationship
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
 

   //Many to One Task - Developer relationship
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "developer_id", nullable = false)
    private Developer developer;
 
    
    //Constructors
    
    
    public Task() {super();}
    
    public Task(Long id, String name, String description, String creator, Date deadline, int completion,
    		    TaskStatus status, String developedBy, Project project, Developer developer) {
    	
    	super(id, name, description, creator, deadline, completion);
    	this.status = status;
    	this.developedBy = developedBy;
    	this.project = project;
    	this.developer = developer;
    }


    // Getters and Setters
    

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


	public TaskStatus getStatus() {
		return status;
	}


	public void setStatus(TaskStatus status) {
		this.status = status;
	}


	public String getDevelopedBy() {
		return developedBy;
	}


	public void setDevelopedBy(String developedBy) {
		this.developedBy = developedBy;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
   
}
