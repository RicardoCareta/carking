package com.pds.carking.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.pds.carking.interfaces.IPerson;
import com.pds.carking.model.abstracts.AUser;
import com.pds.carking.model.enums.SystemAccesses;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "")
public abstract class Employee extends AUser implements IPerson{
	
	@Id
	private UUID id;
	
	private String name;
	private String cellNumber;
	private String cpf;
	
	@Column(insertable = false, updatable = false)
	private String role;
	protected boolean isActive;
	
	public Employee() {
		isActive = true;
		id = UUID.randomUUID();
	}
	//private Employee createdBy
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getRole () {
		return this.role;
	}
	public void setRole (String role) {
		this.role = role;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getCellNumber() {
		return this.cellNumber;
	}

	@Override
	public String getCPF() {
		return this.cpf;
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
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	public abstract SystemAccesses getSystemAccess ();
}
