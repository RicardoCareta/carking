package com.pds.carking.dto;

public class ParkingInformationToDriver {
	
	private String id;
	private VehicleDTO vehicle;
	private CustomerDTO customerDTO;
	private String parkingPlace;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public VehicleDTO getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleDTO vehicle) {
		this.vehicle = vehicle;
	}
	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}
	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}
	public String getParkingPlace() {
		return parkingPlace;
	}
	public void setParkingPlace(String parkingPlace) {
		this.parkingPlace = parkingPlace;
	}
}
