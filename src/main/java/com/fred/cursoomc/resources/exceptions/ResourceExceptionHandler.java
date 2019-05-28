package com.fred.cursoomc.resources.exceptions;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodValidException(MethodArgumentNotValidException e, HttpServletResponse request){
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),
				"Erro de validação",System.currentTimeMillis());
	
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
			
		}
		
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	
	}
	
	@ExceptionHandler(NotReadablePropertyException.class)
	public ResponseEntity<StandardError> NotReadablePropertyException(NotReadablePropertyException e, HttpServletResponse request){
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),
				"Erro de validação de cpf ou cnpj",System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	
	}
	
}
