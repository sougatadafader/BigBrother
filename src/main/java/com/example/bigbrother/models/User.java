package com.example.bigbrother.models;


import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true)
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String imageUrl;
	
	@Column(unique=true)
	private String email;
	
	@Column(columnDefinition = "text")
	private String aboutMe;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Donation>donations;
	
	@OneToMany(mappedBy="user")
	private List<Campaign>campaigns;
	
		
	@CreationTimestamp 
	private Date created;
	
	
	@ManyToMany
	@JoinTable(name="LIKED",
	joinColumns=@JoinColumn(name="USER_ID", 
	referencedColumnName="ID"),
	inverseJoinColumns=@JoinColumn(name=
	   "CAMPAIGN_ID", referencedColumnName="ID"))
	@JsonIgnore
	private List<Campaign>favoriteCampaigns;
	
	private String userRole;
	
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public List<Campaign> getFavoriteCampaigns() {
		return favoriteCampaigns;
	}
	public void setFavoriteCampaigns(List<Campaign> favoriteCampaigns) {
		this.favoriteCampaigns = favoriteCampaigns;
	}
	
	public void addCampaignToFavorite(Campaign campaign) {
		this.favoriteCampaigns.add(campaign);
		if(!campaign.getUsersWhoLiked().contains(this)) {
			campaign.getUsersWhoLiked().add(this);
		}
	}
	
	public void set(User newUser) {
		  this.firstName = newUser.firstName;
		  this.lastName = newUser.lastName;
		  this.imageUrl = newUser.imageUrl;
		  this.username = newUser.username;
		  this.password = newUser.password;
		  this.email = newUser.email;
		  this.aboutMe = newUser.aboutMe;
		  this.userRole = newUser.userRole;
	}

	public List<Donation> getDonations() {
		return donations;
	}
	
	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	public List<Campaign> getCampaigns() {
		return campaigns;
	}
	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
}
