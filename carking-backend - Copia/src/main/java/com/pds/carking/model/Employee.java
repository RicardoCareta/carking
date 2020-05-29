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
import com.pds.carking.interfaces.IUser;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "")
public abstract class Employee implements IUser, IPerson{
	
	@Id
	private UUID id;
	private String username;
	private String password;

	private String name;
	private String cellNumber;
	private String cpf;
	
	@Column(insertable = false, updatable = false)
	private String role;
	
	
	public Employee() {
		id = UUID.randomUUID();
	}
	//private Employee createdBy
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name.toUpperCase();
	}
	
	@Override
	public String getCellNumber() {
		return cellNumber;
	}
	@Override
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	
	@Override
	public String getCPF() {
		return cpf;
	}
	@Override
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
}
