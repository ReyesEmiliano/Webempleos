package com.empleos.empleosWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empleos.empleosWeb.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer>{
	
	
}
