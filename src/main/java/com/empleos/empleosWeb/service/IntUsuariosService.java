package com.empleos.empleosWeb.service;

import java.util.List;

import com.empleos.empleosWeb.model.Usuario;

public interface IntUsuariosService {
	void guardar(Usuario usuario);
	void eliminar(Integer idUsuario);
	List<Usuario> buscarTodos();
	List<Usuario> buscarRegistrados();
	Usuario buscarPorId(Integer idUsuario);
	Usuario buscarPorUsername(String username);
	int bloquear(int idUsuario);
	int activar(int idUsuario);

}
