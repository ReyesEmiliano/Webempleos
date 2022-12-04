package com.empleos.empleosWeb.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.empleos.empleosWeb.model.Categoria;

@Service
public class CategoriasService implements IntCategoriasService {

	
	public List<Categoria> lista;
		
	public CategoriasService() {
		lista = new LinkedList<Categoria>();
		
		Categoria c1= new Categoria();
		c1.setId(1);
		c1.setNombre("Recursos Humanos");
		c1.setDescripcion("Relacionado con psicologos,Administradores");
		
		
		Categoria c2= new Categoria();
		c2.setId(2);
		c2.setNombre("Ventas");
		c2.setDescripcion("Relacionado con productos farmaseuticos");
		
		
		Categoria c3= new Categoria();
		c3.setId(3);
		c3.setNombre("Contabilidad");
		c3.setDescripcion("Relacionado con contabilidad y finanzas");
		
		
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		
	}
	
	@Override
	public List<Categoria> obtenerTodas(){
		
		return lista;
	}

		
	
	@Override
	public void eliminar(Integer idCategoria) {
		// TODO Auto-generated method stub
		
		lista.remove(buscarPorId(idCategoria));

	}

	@Override
	public void guardar(Categoria categoria) {
		// TODO Auto-generated method stub
		
		lista.add(categoria);

	}

	@Override
	public Categoria buscarPorId(Integer idcategoria) {
		// TODO Auto-generated method stub
		
		for(Categoria cat:lista) {
			if(cat.getId()==idcategoria) {
				return cat;
			}
		}
		return null;
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Categoria> buscarTodas(java.awt.print.Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
