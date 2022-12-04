package com.empleos.empleosWeb.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.empleos.empleosWeb.model.Vacante;

public interface IntVacantesService {

	public List<Vacante> obtenerTodas();
	public void agregar(Vacante vacante);
	public void eliminar(int idVacante);
	public Vacante buscarPorId(int idVacante);
	Page<Vacante> buscarTodas(Pageable page);
	Page<Vacante> buscarTodas(java.awt.print.Pageable page);
	
	
}
