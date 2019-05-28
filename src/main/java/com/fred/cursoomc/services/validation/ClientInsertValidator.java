package com.fred.cursoomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fred.cursoomc.domain.enums.TipoCliente;
import com.fred.cursoomc.dto.ClienteNewDTO;
import com.fred.cursoomc.resources.exceptions.FieldMessage;
import com.fred.cursoomc.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
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

		
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}