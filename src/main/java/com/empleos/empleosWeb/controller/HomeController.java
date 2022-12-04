package com.empleos.empleosWeb.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empleos.empleosWeb.model.Categoria;
import com.empleos.empleosWeb.model.Perfil;
import com.empleos.empleosWeb.model.Usuario;
import com.empleos.empleosWeb.model.Vacante;
import com.empleos.empleosWeb.service.IntCategoriasService;
import com.empleos.empleosWeb.service.IntUsuariosService;
import com.empleos.empleosWeb.service.IntVacantesService;



@Controller
public class HomeController {
    
	@Autowired
	private IntVacantesService vacantesService;
	
	@Autowired
	private IntCategoriasService serviceCategorias;

  
    @Autowired
   	private IntUsuariosService serviceUsuarios;
    
    
 
  
	
	@GetMapping("/")
	String mostrarIndex(Model model) {
		List<Vacante> lista = vacantesService.obtenerTodas();
		model.addAttribute("vacantes", lista);
		List<Categoria>lista2=serviceCategorias.obtenerTodas();
		model.addAttribute("categorias", lista2);
		return "home";
	}
	
	
	/**
	 * Método que esta mapeado al botón Ingresar en el menú
	 * @param authentication
	 * @param session
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, HttpSession session) {		
		
		// Como el usuario ya ingreso, ya podemos agregar a la session el objeto usuario.
		String username = authentication.getName();		
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}
		
		if (session.getAttribute("usuario") == null){
			Usuario usuario = serviceUsuarios.buscarPorUsername(username);	
			//System.out.println("Usuario: " + usuario);
			session.setAttribute("usuario", usuario);
		}
		
		return "redirect:/";
	}
	
	/**
	 * Método que muestra el formulario para que se registren nuevos usuarios.
	 * @param usuario
	 * @return
	 */
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	/**
	 * Método que guarda en la base de datos el usuario registrado
	 * @param usuario
	 * @param attributes
	 * @return
	 */
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		// Recuperamos el password en texto plano
		String pwdPlano = "{noop}"+usuario.getPassword();
		usuario.setPassword(pwdPlano);
		// Encriptamos el pwd BCryptPasswordEncoder
	
		// Hacemos un set al atributo password (ya viene encriptado)

		usuario.setEstatus(1); // Activado por defecto
		usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		
		// Creamos el Perfil que le asignaremos al usuario nuevo
		Perfil perfil = new Perfil();
		perfil.setId(2); // Perfil USUARIO
		usuario.agregar(perfil);
		
		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */
		serviceUsuarios.guardar(usuario);
				
		attributes.addFlashAttribute("msg", "Has sido registrado. ¡Ahora puedes ingresar al sistema!");
		
		return "redirect:/login";
	}
	
	/**
	 * Método que muestra el formulario de login personalizado.
	 * @return
	 */
	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	/**
	 * Método personalizado para cerrar la sesión del usuario
	 * @param request
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	

	
	
	
	/**
	 * InitBinder para Strings si los detecta vacios en el Data Binding los settea a NULL
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	
	
}
