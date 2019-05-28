package com.fred.cursoomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fred.cursoomc.domain.Cliente;
import com.fred.cursoomc.domain.enums.TipoCliente;
import com.fred.cursoomc.dto.ClienteNewDTO;
import com.fred.cursoomc.repositories.ClienteRepository;
import com.fred.cursoomc.resources.exceptions.FieldMessage;
import com.fred.cursoomc.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired 
   private ClienteRepository repo;

	
	@Override
	
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
         
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())
				&& !BR.isValidCPF(objDto.getCpfOucnpj()))
				{
			list.add(new FieldMessage("cpfoucnpj","CPF INVALIDO"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod())
				&& !BR.isValidCNPJ(objDto.getCpfOucnpj()))
				{
			list.add(new FieldMessage("cpfoucnpj","CNPJ INVALIDO"));
		}

		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
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