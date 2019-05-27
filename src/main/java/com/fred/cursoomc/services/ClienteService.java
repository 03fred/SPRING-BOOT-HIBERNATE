package com.fred.cursoomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fred.cursoomc.domain.Cliente;
import com.fred.cursoomc.dto.ClienteDTO;
import com.fred.cursoomc.repositories.ClienteRepository;
import com.fred.cursoomc.services.exceptions.DataIntegrationException;
import com.fred.cursoomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	

	public Cliente update(Cliente obj) {
    Cliente newObj = find(obj.getId());
    updateData(newObj,obj);
    return repo.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
	newObj.setNome(obj.getNome());
	newObj.setEmail(obj.getEmail());
		
		
	}


	public void delete(Integer id) {
		Cliente cate = find(id);	
		try {
		repo.delete(cate);
		}catch (DataIntegrityViolationException e) {
               throw new DataIntegrationException("Não é possível excluir porque a entidades relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
	public Cliente fromDto(ClienteDTO obDto) {
		return new Cliente(obDto.getId(),obDto.getNome(),obDto.getEmail(),null,null);
	}
	
	
	
}
