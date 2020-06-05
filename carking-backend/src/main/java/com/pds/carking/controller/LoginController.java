package com.pds.carking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pds.carking.dto.LoginDTO;
import com.pds.carking.services.EmployeeService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<?> loginUser (@Valid @RequestBody LoginDTO loginDTO) throws Exception{
		authenticate(loginDTO.getLogin(), loginDTO.getPassword());
		return ResponseEntity.ok(employeeService.getLoginDTO(loginDTO.getLogin(), loginDTO.getAccess()));
	}
	
	private void authenticate (String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
}
