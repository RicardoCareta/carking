package com.pds.carking.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Vehicle {
	
	@Id
	private UUID id;
	private String model;
	private String brand;
	private String plate;
	private String color;
	@OneToOne
	private Customer customer;
	
	public Vehicle() {
		this.id = UUID.randomUUID();
	}
	
	public UUID getId() {
		return this.id;
	}
	public void setId (UUID id) {
		this.id = id;
	}
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
	public void setClient(Customer customer) {
		this.customer = customer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
