package com.pds.carking.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.pds.carking.interfaces.IPerson;

@Entity
public class Customer implements IPerson{

	@Id
	private UUID id;
	
	private String name;
	private String cellNumber;
	private String CPF;
	private String telephone;
	
	@OneToOne
	private Vehicle vehicle;
	
	public Customer() {
		this.id = UUID.randomUUID();
	}
	
	public UUID getId () {
		return this.id;
	}
	
	public void setId (UUID id) {
		this.id = id;
	}
	
	public void setVehicles (Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Vehicle getVehicles () {
		return this.vehicle;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getCellNumber() {
		return cellNumber;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	@Override
	public String getCPF() {
		return CPF;
	}

	@Override
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
