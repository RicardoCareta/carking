package com.pds.carking.dto;

public class DriverConfirmParkingDTO {

	private String confirmType;
	private String parkingId;
	
	public String getConfirmType() {
		return confirmType;
	}
	public void setConfirmType(String confirmType) {
		this.confirmType = confirmType;
	}
	public String getParkingId() {
		return parkingId;
	}
	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}
}
