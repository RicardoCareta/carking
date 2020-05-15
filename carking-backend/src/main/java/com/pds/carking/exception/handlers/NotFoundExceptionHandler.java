package com.pds.carking.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pds.carking.dto.ErrorDTOResponse;
import com.pds.carking.exception.NotFoundException;

@ControllerAdvice
public class NotFoundExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handlerNotFoundException (NotFoundException ex) {
		final String errorMessage = ex.getMessage();
		
		ErrorDTOResponse errorDTOResponse = new ErrorDTOResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTOResponse);
	}
}
