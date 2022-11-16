package com.mar.agenda.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mar.agenda.models.Contacto;
import com.mar.agenda.services.ContactoService;

@Controller
public class ContactoController {

	@Autowired
	private ContactoService contactoService;
	
	//lista todos los contactos de pagina principal
	@GetMapping("/")
	ModelAndView inicio() {
		
		List<Contacto> contactos = contactoService.listarContactos();
		
		return new ModelAndView("index")
				.addObject("contactos",contactos);
	}
	
	
	//agrega un nuevo contacto
	@GetMapping("/nuevo")
	ModelAndView nuevo() {
		return new ModelAndView("form")
				.addObject("contacto", new Contacto());
	}
	
	
	@PostMapping("/nuevo")
	ModelAndView crear(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes ra ) {
		
		if (bindingResult.hasErrors()) {
			System.out.println("valida ERROR");
			return new ModelAndView("form")
					.addObject("contacto", contacto);
		}
		
		contactoService.agregarContacto(contacto);
		ra.addFlashAttribute("msgExito", "El contacto ha sido guardado exitosamente.");
		return new ModelAndView("redirect:/");
	}
	
	
	//edita un contacto
	@GetMapping("/editar/{id}")
	ModelAndView editar(@PathVariable Integer id) {
		
		Optional<Contacto> contacto = contactoService.mostrarContacto(id);
		return new ModelAndView("form")
				.addObject("contacto", contacto);
	}
	
	
	@PostMapping("/editar/{id}")
	ModelAndView actualizar(
			@PathVariable Integer id,
			@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes ra ) {
		
		
		contactoService.mostrarContacto(id);
		
		if (bindingResult.hasErrors()) {
			System.out.println("valida ERROR");
			return new ModelAndView("form")
					.addObject("contacto", contacto);
		}
		
		contacto.setId(id);
		
		contactoService.agregarContacto(contacto);
		ra.addFlashAttribute("msgExito", "El contacto ha sido actualizado exitosamente.");
		return new ModelAndView("redirect:/");
	}
	
	
	@GetMapping("/eliminar/{id}")
	String eliminar(
			@PathVariable Integer id, RedirectAttributes ra ) {
		
		
		//contactoService.mostrarContacto(id);		
		
		contactoService.borrarContacto(id);
		ra.addFlashAttribute("msgExito", "El contacto ha sido borrado exitosamente.");
		return ("redirect:/");
	}
	
}
