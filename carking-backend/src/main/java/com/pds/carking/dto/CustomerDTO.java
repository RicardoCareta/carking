package com.pds.carking.dto;

import javax.validation.constraints.NotEmpty;

public class CustomerDTO {

	@NotEmpty(message = "Client name is required")
	private String name;
	@NotEmpty(message = "Client cell number is required")
	private String cellNumber;
	@NotEmpty(message = "Client cpf is required")
	private String cpf;
	private String telephone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCellNumber() {
		return cellNumber;
	}
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
