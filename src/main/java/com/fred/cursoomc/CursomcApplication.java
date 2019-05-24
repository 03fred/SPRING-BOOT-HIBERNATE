package com.fred.cursoomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fred.cursoomc.domain.Categoria;
import com.fred.cursoomc.domain.Cidade;
import com.fred.cursoomc.domain.Estado;
import com.fred.cursoomc.domain.Produto;
import com.fred.cursoomc.repositories.CategoriaRepository;
import com.fred.cursoomc.repositories.CidadeRepository;
import com.fred.cursoomc.repositories.EstadoRepository;
import com.fred.cursoomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired 
	private CidadeRepository cidadeRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 =  new Categoria(null,"Informatica");
		Categoria cat2 =  new Categoria(null,"Escritório");
        Produto p1 = new Produto(null,"computador",2000);
        Produto p2 = new Produto(null,"impressora",800);
        Produto p3 = new Produto(null,"mouse",80);

        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));
        
        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");
        
        Cidade c1 = new Cidade(null,"Uberlândia",est1);
        Cidade c2 = new Cidade(null,"São Paulo",est2);
        Cidade c3 = new Cidade(null,"Campinas",est2);
         
        
        
        categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
	}

	
}

