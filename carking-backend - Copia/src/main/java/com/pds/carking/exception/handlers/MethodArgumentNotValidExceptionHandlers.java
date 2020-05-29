package com.pds.carking.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pds.carking.dto.ErrorDTOResponse;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandlers {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
		
		final ObjectError error = ex.getBindingResult().getAllErrors().stream().findFirst().get();
		final String errorMessage = error.getDefaultMessage();
		
		ErrorDTOResponse errorDTOResponse = new ErrorDTOResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTOResponse);
	}
}
