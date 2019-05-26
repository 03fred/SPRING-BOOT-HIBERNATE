package com.fred.cursoomc.resources.exceptions;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fred.cursoomc.services.exceptions.DataIntegrationException;
import com.fred.cursoomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFund(ObjectNotFoundException e, HttpServletResponse request){
		
	StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis());
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	
	}
	
	@ExceptionHandler(DataIntegrationException.class)
	public ResponseEntity<StandardError> dataIntegration(DataIntegrationException e, HttpServletResponse request){
	StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),e.getMessage(),System.currentTimeMillis());
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	
	}
}
