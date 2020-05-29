package com.pds.carking.dto;

import javax.validation.constraints.NotEmpty;

public class VehicleDTO {
	
	@NotEmpty(message = "Vehicle model is required")
	private String model;
	@NotEmpty(message = "Vehicle brand is required")
	private String brand;
	@NotEmpty(message = "Vehicle plate is required")
	private String plate;
	@NotEmpty(message = "Vehicle color is required")
	private String color;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}
