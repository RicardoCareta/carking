package com.pds.carking.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EmployeeDTO {
	
	private UUID id;
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Username is required")
	private String username;
	@NotBlank(message = "Password is required")
	private String password;
	
	@NotBlank(message = "CPF is required")
	private String cpf;
	@NotBlank(message = "CellNumber is required")
	@JsonAlias(value = "cellnumber")
	private String cellNumber;
	
	@NotBlank(message = "Type is required")
	private String type;
	
	public UUID getId () {
		return id;
	}
	public void setId (UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCPF () {
		return this.cpf;
	}
	public void setCPF (String cpf) {
		this.cpf = cpf;
	}
	public String getCellNumber () {
		return this.cellNumber;
	}
	public void setCellNumber (String cellNumber) {
		this.cellNumber = cellNumber;
	}
	public String getType () {
		return this.type;
	}
	public void setType (String type) {
		this.type = type;
	}
}
