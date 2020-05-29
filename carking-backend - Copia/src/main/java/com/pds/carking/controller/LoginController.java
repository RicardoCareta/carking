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
import com.pds.carking.dto.LoginDTOResponse;
import com.pds.carking.security.JwtTokenUtil;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping
	public ResponseEntity<?> loginUser (@Valid @RequestBody LoginDTO loginDTO) {
		authenticate(loginDTO.getLogin(), loginDTO.getPassword());
		final String token = jwtTokenUtil.generateToken(loginDTO.getLogin());
		return ResponseEntity.ok(new LoginDTOResponse(token));
	}
	
	private void authenticate (String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
}
