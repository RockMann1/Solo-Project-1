package com.honeydolist.models;

import java.util.Date;

//import com.honeydolist.models.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tasks")
public class Task {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank(message="Please name your task.")
    private String name;
	
	@NotNull(message="Please enter price of task.")
    @Min(value=0, message="Task must at least cost 0")
    @Max(value=5000, message="Task can not be over 5000$")
    private Integer taskPrice;
	
	@NotBlank(message="Need the location!")
    private String locationName;
	
	@NotBlank(message="Require some helpful notes.")
    private String notesField;
	
	//Create created at and updated at entries.
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
  //Create many to one relationship 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
	
    public void Task() {
    }
	
    @PrePersist
    protected void onCreate() {
    	this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
    	this.createdAt = new Date();
    }

    //Getters and Setters
   
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

	public Integer getTaskPrice() {
		return taskPrice;
	}

	public void setTaskPrice(Integer taskPrice) {
		this.taskPrice = taskPrice;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getNotesField() {
		return notesField;
	}

	public void setNotesField(String notesField) {
		this.notesField = notesField;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}
