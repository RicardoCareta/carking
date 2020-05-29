package com.pds.carking.model.enums;

public enum Roles {

	MANAGER (Values.MANAGER),
	ATTENDANT (Values.ATTENDANT),
	DRIVER (Values.DRIVER);
	
	private String role;
	
	Roles (String role) {
		this.role = role;
	}
	
	public String getRole () {
		return this.role;
	}
	
	public static class Values {
		public static final String ATTENDANT = "attendant";
		public static final String MANAGER = "manager";
		public static final String DRIVER = "driver";
	}
}
