package com.example.bigbrother.models;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Campaign {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String header;
	
	@Column(columnDefinition = "text")
	private String text;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	
	@ManyToMany(mappedBy="favoriteCampaigns")
	@JsonIgnore
	private List<User> usersWhoLiked;
	
	

	private int creatorId;


	@OneToOne
	private Dependent dependent;

	@CreationTimestamp 
	private Date created;
	
	private String imageUrl;
	
	private boolean enabled = true;
	
	
	@OneToMany(mappedBy="campaign")
	private List<Donation> donations;

	private float currentValue;

	private float targetValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreator() {
		return creatorId;
	}
	
	public void setCreator(int creatorId) {
		this.creatorId = creatorId;
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

	
	public float getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(float currentValue) {
		this.currentValue = currentValue;
	}

	public float getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(float targetValue) {
		this.targetValue = targetValue;
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
	
	public List<User> getUsersWhoLiked() {
		return usersWhoLiked;
	}

	public void setUsersWhoLiked(List<User> usersWhoLiked) {
		this.usersWhoLiked = usersWhoLiked;
	}
	
	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	public void addToFavoriteByUser(User user) {
        this.usersWhoLiked.add(user);
        System.out.println(this.id);
        if(!user.getFavoriteCampaigns().contains(this)) {
        	System.out.println("In model "+user.getUsername());
            user.getFavoriteCampaigns().add(this);
        }
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
