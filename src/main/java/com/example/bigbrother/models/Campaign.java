package com.example.bigbrother.models;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Campaign {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String header;
	
	private String text;
	
	@ManyToOne
	private User user;
	
	@OneToOne
	private Dependent dependent;
	
	@CreationTimestamp 
	private Date created;
	
	private String imageUrl;
	
	private boolean enabled = true;

	private float currentValue;

	private float targetValue;

	public int getId() {
		return id;
	}

	

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public float getCurrent() {
		return currentValue;
	}

	public void setCurrent(float current) {
		this.currentValue = current;
	}

	public float getTarget() {
		return targetValue;
	}

	public void setTarget(float target) {
		this.targetValue = target;
	}
	
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	public Dependent getDependent() {
		return dependent;
	}

	public void setDependent(Dependent dependent) {
		this.dependent = dependent;
	}
	
	public void set(Campaign newCampaign) {
		  this.id = newCampaign.id!=0?newCampaign.id:this.id;
		  this.header = newCampaign.header!=null?newCampaign.header:this.header;
		  this.text = newCampaign.text!=null?newCampaign.text:this.text;
		  this.created = newCampaign.created!=null?newCampaign.created:this.created;
		  this.imageUrl = newCampaign.imageUrl!=null?newCampaign.imageUrl:this.imageUrl;
		  this.enabled = newCampaign.enabled;
		  this.currentValue = newCampaign.currentValue;
		  this.targetValue = newCampaign.targetValue;

	}

}
