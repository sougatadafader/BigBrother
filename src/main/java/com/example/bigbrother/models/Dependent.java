package com.example.bigbrother.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Dependent {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	private Campaign campaign;
	
	private int creatorId;

	private String firstName;
	
	private String lastName;
	
	private String landmark;
	
	private String zipcode;
	
	private boolean enabled = true;

	@CreationTimestamp 
	private Date created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
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

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	public void set(Dependent newDependent) {
		this.id = newDependent.id!=0?newDependent.id:this.id;
		  this.firstName = newDependent.firstName!=null?newDependent.firstName:this.firstName;
		  this.lastName = newDependent.lastName!=null?newDependent.lastName:this.lastName;
		  this.landmark = newDependent.landmark!=null?newDependent.landmark:this.landmark;
		  this.zipcode = newDependent.zipcode!=null?newDependent.zipcode:this.zipcode;
	}
}
