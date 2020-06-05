package com.pds.carking.model.enums;

public enum SystemAccesses {
	
	MOBILE("mobile"),
	WEB("web");
	
	private String access;
	
	private SystemAccesses(String access) {
		this.access = access;
	}
	public String getAccess () {
		return this.access;
	}
}
