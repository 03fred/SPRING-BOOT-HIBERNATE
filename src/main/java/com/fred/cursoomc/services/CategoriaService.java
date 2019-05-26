package com.fred.cursoomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fred.cursoomc.domain.Categoria;
import com.fred.cursoomc.repositories.CategoriaRepository;
import com.fred.cursoomc.services.exceptions.DataIntegrationException;
import com.fred.cursoomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
     find(obj.getId());
    return repo.save(obj);
	}

	public void delete(Integer id) {
		Categoria cate = find(id);	
		try {
		repo.delete(cate);
		}catch (DataIntegrityViolationException e) {
               throw new DataIntegrationException("Não é possível excluir uma categoria com produtos");
			
		
		}
	}
	
	
}
