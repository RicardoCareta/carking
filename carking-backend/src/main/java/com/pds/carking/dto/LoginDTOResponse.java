package com.pds.carking.dto;

public class LoginDTOResponse {

	private String token;

	public LoginDTOResponse(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
