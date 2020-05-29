package com.pds.carking.exception.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pds.carking.dto.ErrorDTOResponse;

public class AccessDeniedExceptionHandler {

	public AccessDeniedExceptionHandler(HttpServletResponse response, String errorMessage) throws JsonProcessingException, IOException {	
		ErrorDTOResponse errorDTOResponse = new ErrorDTOResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
		response.setContentType("application/json");
		response.setStatus(errorDTOResponse.getStatus());
		response.getWriter().write(new ObjectMapper().writeValueAsString(errorDTOResponse));
	}
	
}
