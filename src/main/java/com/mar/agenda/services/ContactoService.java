package com.mar.agenda.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mar.agenda.models.Contacto;
import com.mar.agenda.repositories.ContactoRepository;

@Service
public class ContactoService {

	@Autowired
	private ContactoRepository ContactoRepo;
	
	//listar todos los contactos
	public List<Contacto> listarContactos(){		
		return ContactoRepo.findAll();
	}
	
	
	//agregar un contacto a bbdd
	public void agregarContacto(Contacto contacto) {		
		ContactoRepo.save(contacto);
	}
	
	
	// elimina un contacto de la bbdd por el (id)
	public void borrarContacto(Integer id) {	
		ContactoRepo.deleteById(id);
	}
	
	
	//mostrar un contacto por su (id)
	public Optional<Contacto> mostrarContacto(Integer id) {	
		return ContactoRepo.findById(id);
	}
	
}
