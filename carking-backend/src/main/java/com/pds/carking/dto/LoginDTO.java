package com.pds.carking.dto;

import javax.validation.constraints.NotBlank;

public class LoginDTO {
	
	@NotBlank(message="Login is required")
	private String login;
	
	@NotBlank(message="Password is required")
	private String password;
	
	private String access;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
}
