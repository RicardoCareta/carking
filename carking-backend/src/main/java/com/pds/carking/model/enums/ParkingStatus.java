package com.pds.carking.model.enums;

public enum ParkingStatus {

	PARK_ENTER ("Park enter"),
	PARKED ("Parked"),
	PARK_EXIT ("Park exit");
	
	private String status;
	
	private ParkingStatus(String status) {
		this.status = status;
	}
	
	public String getStatus () {
		return this.status;
	}
}
