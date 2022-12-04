package com.empleos.empleosWeb.controller;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empleos.empleosWeb.model.Vacante;
import com.empleos.empleosWeb.service.IntCategoriasService;
import com.empleos.empleosWeb.service.IntVacantesService;
import com.empleos.empleosWeb.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	private IntVacantesService vacantesService;
	
	@Autowired
	private IntCategoriasService categoriasService;
	
	
	//////////////////////
	
	@PostMapping("/guardar")
	public String guardar(Vacante vacante,BindingResult br, RedirectAttributes atributo, @RequestParam("archivoImagen") MultipartFile multiPart, Model model) {
		
		if(br.hasErrors()) {
			for(ObjectError error: br.getAllErrors()) {
				System.out.print("Error :" + error.getDefaultMessage());
				
			}
			model.addAttribute("categorias", categoriasService.obtenerTodas()); 
			return "vacantes/formVacante";
		}
		
		System.out.print(vacante);
		//vacante.setId(vacantesService.obtenerTodas().size()+1);
		System.out.print(vacante);
	
		if (!multiPart.isEmpty()) {
			Path directorioImagenes= Paths.get("src//main//resources//static/images");
            String rutaAbsoluta=directorioImagenes.toFile().getAbsolutePath();
			
			try {
				byte[] bytesImg= multiPart.getBytes();
				Path rutaCompleta=Paths.get(rutaAbsoluta + "//" + multiPart.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				vacante.setImagen(multiPart.getOriginalFilename());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "c:/empleos/img-vacantes/"; // Windows
			
			//String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			//if (nombreImagen != null){ // La imagen si se subio
			// Procesamos la variable nombreImagen
			//vacante.setImagen(nombreImagen);
			//}
			}
		vacantesService.agregar(vacante);
		atributo.addFlashAttribute("msg","Vacante Registrado");
		return "redirect:/vacantes/indexPaginate";
	}
	
	//////////////
	
	@GetMapping("/detalle")
	public String detalle(@RequestParam("id") int idVacante, Model model) {
		
		Vacante vacante =vacantesService.buscarPorId(idVacante);
		model.addAttribute("vacante", vacante);
		return "vacantes/detalle";
	}
	
	////////////////
	
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Vacante>lista = vacantesService.buscarTodas(page);
	model.addAttribute("vacantes", lista);
	return "vacantes/listVacantes";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport(){
			@Override
			public void setAsText(String text) throws IllegalArgumentException{
				setValue(LocalDate.parse(text,DateTimeFormatter.ofPattern("dd-MM-yyyy")));
				
			}
			@Override
			
			public String getAsText()throws IllegalArgumentException{
				return DateTimeFormatter.ofPattern("dd-MM-yyyy").format((LocalDate)getValue());
			}
			
			
		});
	}
	
	
	///////////////////
	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model){
		List<Vacante> lista = vacantesService.obtenerTodas();
		/*for(Vacante v:lista) {
			System.out.print(v);
		}
		System.out.print(lista);*/
		System.out.print(lista);
		model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") int idVacante,RedirectAttributes atributo) {
		vacantesService.eliminar(idVacante);
		atributo.addFlashAttribute("msg","Vacante eliminada");
		return "redirect:/vacantes/indexPaginate";
	}
	
	
	
	
	
	
	

	@GetMapping("/crear")
	public String crearCategoria(Vacante vacante, Model model) {
		model.addAttribute("categorias", categoriasService.obtenerTodas());
		return "vacantes/formVacante";
	}
	
	
	
	
}
