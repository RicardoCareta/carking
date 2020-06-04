package com.pds.carking.dto;

public class ParkingParkingPlaceFilterDTO {

	private String id;
	private VehicleDTO vehicle;
	private String parkedDate;
	private CustomerDTO customerDTO;
	private String parkingPlace;
	private String price;
	
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
	public String getParkedDate() {
		return parkedDate;
	}
	public void setParkedDate(String parkedDate) {
		this.parkedDate = parkedDate;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
