package com.fred.cursoomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fred.cursoomc.domain.Categoria;
import com.fred.cursoomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer >{

}
