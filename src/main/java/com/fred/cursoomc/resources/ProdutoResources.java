package com.fred.cursoomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fred.cursoomc.domain.Produto;
import com.fred.cursoomc.dto.CategoriaDTO;
import com.fred.cursoomc.dto.ProdutoDTO;
import com.fred.cursoomc.resources.utils.URL;
import com.fred.cursoomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResources {

	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {

		Produto obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			 @RequestParam(value="nome", defaultValue = "")String nome,
			 @RequestParam(value="categorias", defaultValue = "")String categorias,
			 @RequestParam(value="page", defaultValue = "0")Integer page,
			 @RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
			 @RequestParam(value="orderBy", defaultValue = "nome")  String orderBy,
			 @RequestParam(value="direction", defaultValue = "ASC")  String direction) {
		String nomeParam = URL.encodeParam(nome);
		List<Integer>ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeParam, ids,page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

}
