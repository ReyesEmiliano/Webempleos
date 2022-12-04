package com.empleos.empleosWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empleos.empleosWeb.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

}
