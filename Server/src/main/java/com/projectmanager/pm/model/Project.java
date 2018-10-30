package com.projectmanager.pm.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "projects")
public class Project extends Product{
	
	private String githubURL;
	
	@NotNull
	private Boolean personal;
	
    
    // Many to many  Project - Developer relationship
  
    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {
    CascadeType.PERSIST,
    CascadeType.MERGE
    })
    @JoinTable( name = "project_developers",
    			joinColumns = { @JoinColumn(name = "project_id") },
    			inverseJoinColumns = { @JoinColumn(name = "developer_id") }
               )
 
    private Set<Developer> developers = new HashSet<>();
    
    
	 // One to Many Project - Task relationship
	 
	@OneToMany( mappedBy = "project", 
		        cascade = CascadeType.ALL, 
		        orphanRemoval = true )
     private Set<Task> tasks = new HashSet<>();
	
    
    
    // Constructors
	
  
	public Project() {super();}
	
	public Project(Long id, String name, String description, String creator, Date deadline, Integer completion, 
			       String githubURL, Boolean personal, Set<Developer> developers, Set<Task>tasks ) {
		super(id, name, description, creator, deadline, completion);
		this.githubURL = githubURL;
		this.personal = personal;
		this.developers = developers;
		this.tasks = tasks;
	}

	
	 // Getters and Setters
	
	
	public String getGithubURL() {
		return githubURL;
	}

	public void setGithubURL(String githubURL) {
		this.githubURL = githubURL;
	}

	public Boolean getPersonal() {
		return personal;
	}

	public void setPersonal(Boolean personal) {
		this.personal = personal;
	}

	public Set<Developer> getDevelopers() {
		return developers;
	}

	public void setDevelopers(Set<Developer> developers) {
		this.developers = developers;
	}


	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
    
	
	// Methods 
	
	public void addTask(Task task) {
        tasks.add(task);
        task.setProject(this);
    }
 
    public void removeTask(Task task) {
        tasks.remove(task);
        task.setProject(null);
    }
    
    
   public void setCompletion() {
    	Integer value = 0;
    	if ( !this.tasks.isEmpty()) {
    		for(Task task : this.tasks) {
    			value += task.getCompletion();
    		}
    		int projectComp = value / this.tasks.size();
    		super.setCompletion(projectComp);
    	} else {
    		super.setCompletion(value);
    	}
    }

}
