package com.empleos.empleosWeb.service.db;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.empleos.empleosWeb.model.Vacante;
import com.empleos.empleosWeb.repository.VacantesRepository;
import com.empleos.empleosWeb.service.IntVacantesService;

@Service
@Primary
public class VacantesServiceCrud implements IntVacantesService {
	
	@Autowired
	VacantesRepository repoVacantes;

	@Override
	public List<Vacante> obtenerTodas() {
		return repoVacantes.findAll();
	}

	@Override
	public void agregar(Vacante vacante) {
		repoVacantes.save(vacante);

	}

	@Override
	public void eliminar(int idVacante) {
		repoVacantes.deleteById(idVacante);

	}

	@Override
	public Vacante buscarPorId(int idVacante) {
		Optional<Vacante> optional = repoVacantes.findById(idVacante);
		if(optional.isPresent()) {
			return optional.get();
			
		}
		return null;
	}
	
	@Override
	public Page<Vacante> buscarTodas(java.awt.print.Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Vacante>buscarTodas(Pageable page) {
	return repoVacantes.findAll(page);
	}

	
	
}
