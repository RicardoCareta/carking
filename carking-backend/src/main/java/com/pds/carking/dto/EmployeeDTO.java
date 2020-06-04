package com.pds.carking.dto;

import javax.validation.constraints.NotBlank;

import com.pds.carking.dto.abstracts.EmployeeBaseDTO;

public class EmployeeDTO extends EmployeeBaseDTO{

	private String id;
	
	@NotBlank(message = "Role is required")
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
