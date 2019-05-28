package com.fred.cursoomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fred.cursoomc.domain.Cliente;
import com.fred.cursoomc.dto.ClienteDTO;
import com.fred.cursoomc.repositories.ClienteRepository;
import com.fred.cursoomc.resources.exceptions.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired 
   private ClienteRepository repo;

	
	@Override
	
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
      
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null && aux.getEmail() != objDto.getEmail()) {
			list.add(new FieldMessage("email", "Email já existe"));			
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}