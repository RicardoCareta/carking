package com.pds.carking.model.enums;

public enum ParkingPlaceStatus {

	EMPTY ("empty"),
	BUSY ("busy");
	
	private String status;
	
	private ParkingPlaceStatus(String status) {
		this.status = status;
	}
	
	public String getStatus () {
		return this.status;
	}
}
