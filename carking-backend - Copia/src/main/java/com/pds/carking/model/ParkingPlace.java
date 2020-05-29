package com.pds.carking.model;

import javax.persistence.Embeddable;

@Embeddable
public class ParkingPlace {
	
	public ParkingPlace() {	}
	public ParkingPlace (String name) {
		this.name = name;
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
