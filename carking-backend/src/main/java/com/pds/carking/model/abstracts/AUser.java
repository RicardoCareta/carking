package com.pds.carking.model.abstracts;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AUser {
	
	protected String username;
	protected String password;
	
	public String getUsername () {
		return username;
	}
	public String getPassword () {
		return password;
	}
	public void setUsername (String username) {
		this.username = username;
	}
	public void setPassword (String password) {
		this.password = password;
	}
}
