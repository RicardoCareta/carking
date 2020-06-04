package com.pds.carking.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pds.carking.dto.ErrorDTOResponse;
import com.pds.carking.exception.InvalidInputDataException;

@ControllerAdvice
public class InvalidInputDataExceptionHandler {
	
	@ExceptionHandler(InvalidInputDataException.class)
	public ResponseEntity<?> handlerInvalidInputDataException (InvalidInputDataException ex) {
		final String errorMessage = ex.getMessage();
		
		ErrorDTOResponse errorDTOResponse = new ErrorDTOResponse(HttpStatus.I_AM_A_TEAPOT.value(), errorMessage);
		
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(errorDTOResponse);
	}
}
