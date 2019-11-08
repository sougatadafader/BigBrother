package com.example.bigbrother.models;
import javax.persistence.Entity;

@Entity
public class SystemUser extends User{
	
	private boolean enabled = true;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

}
