package com.fred.cursoomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private List<FieldMessage> erros = new ArrayList<>();
	
	
	public ValidationError(Integer status, String msg, Long timestamp) {
		super(status, msg, timestamp);
		// TODO Auto-generated constructor stub
	}
 
	
	public void addError(String fieldName,String messagem) {
		erros.add(new FieldMessage(fieldName, messagem));
	}

	private static final long serialVersionUID = 1L;


	public List<FieldMessage> getErros() {
		return erros;
	}


	

}
