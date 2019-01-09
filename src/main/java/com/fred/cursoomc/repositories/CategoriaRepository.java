package com.fred.cursoomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fred.cursoomc.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer >{

}
