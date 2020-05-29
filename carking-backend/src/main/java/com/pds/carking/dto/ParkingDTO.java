package com.pds.carking.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ParkingDTO {

	@NotNull(message = "Customer is required")
	@Valid
	private CustomerDTO customer;
	
	@NotEmpty(message = "ParkingPlace is required")
	private String parkingPlace;
	
	@NotEmpty(message = "Driver is required")
	private String driver;
	
	@NotEmpty(message = "Attendant is required")
	private String attendant;
	
	@NotNull(message = "Vehicle is required")
	@Valid
	private VehicleDTO vehicle;
	
	public String getParkingPlace() {
		return parkingPlace;
	}
	public void setParkingPlace(String parkingPlace) {
		this.parkingPlace = parkingPlace;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getAttendant() {
		return attendant;
	}
	public void setAttendant(String attendant) {
		this.attendant = attendant;
	}
	public VehicleDTO getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleDTO vehicle) {
		this.vehicle = vehicle;
	}
	public CustomerDTO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
}
