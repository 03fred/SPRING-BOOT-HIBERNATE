package com.fred.cursoomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fred.cursoomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer >{

}
