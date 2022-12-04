package com.empleos.empleosWeb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.empleos.empleosWeb.model.Solicitud;

public interface IntSolicitudesService {

	void guardar(Solicitud solicitud);
	void eliminar(Integer idSolicitud);
	List<Solicitud> buscarTodas();
	Solicitud buscarPorId(Integer idSolicitud);
	Page<Solicitud> buscarTodas(Pageable page);
}
