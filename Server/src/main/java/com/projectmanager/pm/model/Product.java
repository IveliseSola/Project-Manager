package com.projectmanager.pm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.userdetails.User;

import javax.annotation.processing.Completion;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)

public class Product implements Serializable{
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotNull
		@Size(max = 100)
		@Column(unique = true)
		private String name;

		@NotNull
		@Size(max = 250)
		private String description;
		
		@CreatedBy
		private String created;

		@NotNull
	    private Date deadline;
		
		private int completion = 0;
		
	 	@Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "created_at", nullable = false, updatable = false)
	    @CreatedDate
	    private Date createdAt;

	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "updated_at", nullable = false)
	    @LastModifiedDate
	    private Date updatedAt;
	    
	    
	    
	    //Constructors
	    
	    public Product() {}
	    
	    public Product(Long id, String name, String description, String creator, Date deadline, int completion) {
			super();
			
			if (completion < 0 || completion > 100) {
		         throw new IllegalArgumentException();
		      } else {
		         this.completion = completion;
		      }
			this.id = id;
			this.name = name;
			this.description = description;
			this.created = creator;
			this.deadline = deadline;
		}

 
	    // Getters and setters

		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}



		public String getDescription() {
			return description;
		}



		public void setDescription(String description) {
			this.description = description;
		}



		public Date getDeadline() {
			return deadline;
		}



		public void setDeadline(Date deadline) {
			this.deadline = deadline;
		}



		public Date getCreatedAt() {
			return createdAt;
		}



		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}



		public Date getUpdatedAt() {
			return updatedAt;
		}



		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}



		public String getCreated() {
			return created;
		}


		public void setCreated(String creator) {
			this.created = creator;
		}


		public int getCompletion() {
			return completion;
		}


		public void setCompletion(int completion) {
			if (completion < 0 || completion > 100) {
		         throw new IllegalArgumentException();
		      } else {
		    	  this.completion = completion;
		    }
			
		}

}
