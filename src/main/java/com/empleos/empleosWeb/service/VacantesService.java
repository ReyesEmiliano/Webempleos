package com.empleos.empleosWeb.service;

import java.awt.print.Pageable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.empleos.empleosWeb.model.Categoria;
import com.empleos.empleosWeb.model.Vacante;

@Service
public class VacantesService implements IntVacantesService {
	
	private List<Vacante> lista;
	
	
    public VacantesService() {
    	lista=new LinkedList<Vacante>();
    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	
    	try {
    		Vacante v1=new Vacante();
    		v1.setId(1);
    		v1.setNombre("Desarrollador Java");
    		v1.setDescripcion("Desarrollo de aplicaciones web");
    		v1.setFecha(LocalDate.parse("19-09-2022",formato));
    		v1.setSalario(12000.0);
    		v1.setEstatus("Creada");
    		v1.setDestacado(1);
    		v1.setDetalles("<h1>Requisitos</h1><ul><li>Amplia Experiencia</li></ul>");
    		v1.setImagen("logo1.png");
    		
    		Categoria cat1 = new Categoria();
    		cat1.setId(1);
    		v1.setCategoria(cat1);
    		
    		Vacante v2=new Vacante();
    		v2.setId(2);
    		v2.setNombre("Contador Publico");
    		v2.setDescripcion("Contabilidad y Finanzas");
    		v2.setFecha(LocalDate.parse("18-09-2022",formato));
    		v2.setSalario(10000.0);
    		v2.setEstatus("Creada");
    		v2.setDestacado(2);
    		v2.setDetalles("<h1>Requisitos</h1><ul><li>Ingeniero en sistemas</li></ul>");
    		Categoria cat2 = new Categoria();
    		cat2.setId(3);
    		v2.setCategoria(cat2);
    		
    		Vacante v3=new Vacante();
    		v3.setId(3);
    		v3.setNombre("Desarrollo de aplicaciones iot");
    		v3.setDescripcion("Mantenimiento de redes");
    		v3.setFecha(LocalDate.parse("17-09-2022",formato));
    		v3.setSalario(8000.0);
    		v3.setEstatus("Creada");
    		v3.setDestacado(1);
    		v3.setDetalles("<h1>Requisitos</h1><ul><li>De preferencia femenino</li></ul>");
    		v3.setImagen("logo3.png");
    		Categoria cat3 = new Categoria();
    		cat3.setId(5);
    		v3.setCategoria(cat3);
    		
    		
    		lista.add(v1);
    		lista.add(v2);
    		lista.add(v3);
    	}catch(DateTimeException ex) {
    		System.out.print(ex.getMessage());
    		
    	}
    	
    }
	
	@Override
	public List<Vacante> obtenerTodas() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public void agregar(Vacante vacante) {
		lista.add(vacante);

	}

	@Override
	public void eliminar(int idVacante) {
		lista.remove(buscarPorId(idVacante));

	}

	@Override
	public Vacante buscarPorId(int idVacante) {
		Vacante vacante=null;
		for(Vacante v : lista) {
			if(v.getId()== idVacante) {
				vacante=v;
			}
			
		}
		return vacante;
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Vacante> buscarTodas(org.springframework.data.domain.Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
