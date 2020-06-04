package com.pds.carking.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pds.carking.dto.EmployeeDTO;
import com.pds.carking.dto.EmployeeUpdateDTO;
import com.pds.carking.services.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<?> storeEmployee (@Valid @RequestBody EmployeeDTO employeeDTO, @RequestHeader("Authorization") String token) throws Exception {
		final String idEmployee = employeeService.storeEmployee(employeeDTO);
		Map<String, String> idMapReturn = new HashMap<String, String>();
		idMapReturn.put("id", idEmployee);
		return ResponseEntity.status(HttpStatus.CREATED).body(idMapReturn);
	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<?> getEmployee (@PathVariable String employeeId) {
		return ResponseEntity.ok(employeeService.getEmployee(employeeId));
	}
	
	@GetMapping
	public ResponseEntity<?> getAllEmployee () {
		return ResponseEntity.ok(employeeService.getAllEmployee());
	}
	
	@GetMapping("/info")
	public ResponseEntity<?> getEmployeeToken (@RequestHeader ("Authorization") String token) throws Exception{
		return ResponseEntity.ok(employeeService.getEmployeeToken(token));
	}
	
	@PatchMapping()
	public ResponseEntity<?> updateEmployee (@Valid @RequestBody EmployeeUpdateDTO employeeDTO, @RequestHeader ("Authorization") String token) throws Exception {
		return ResponseEntity.ok(employeeService.updateEmployee(employeeDTO, token));
	}
}
