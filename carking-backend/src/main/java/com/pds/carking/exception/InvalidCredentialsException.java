package com.pds.carking.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidCredentialsException extends AuthenticationException{
	
	private static final long serialVersionUID = -7504910832562626540L;
	
	public InvalidCredentialsException(String message) {
		super(message);
	} 
}
