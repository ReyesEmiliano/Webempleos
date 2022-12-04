package com.empleos.empleosWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empleos.empleosWeb.model.Solicitud;

public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {

}
