package com.fred.cursoomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fred.cursoomc.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer >{

}
