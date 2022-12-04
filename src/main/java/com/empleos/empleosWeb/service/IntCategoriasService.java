package com.empleos.empleosWeb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.empleos.empleosWeb.model.Categoria;


public interface IntCategoriasService {

	
	public List<Categoria> obtenerTodas();
	public void eliminar(Integer idCategoria);
	public void guardar(Categoria categoria);
	public Categoria buscarPorId(Integer idcategoria);
	Page<Categoria> buscarTodas(Pageable page);
	Page<Categoria> buscarTodas(java.awt.print.Pageable page);
	
}
