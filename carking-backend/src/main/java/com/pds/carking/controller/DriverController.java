package com.pds.carking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pds.carking.services.EmployeeService;

@RequestMapping("/driver")
@Controller
public class DriverController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/no-busy")
	public ResponseEntity<?> getNoBusyDrivers() {
		return ResponseEntity.ok(employeeService.getNoBusyDrivers());
	}
}
