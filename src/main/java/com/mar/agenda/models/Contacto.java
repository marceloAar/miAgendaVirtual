package com.mar.agenda.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Contacto {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String nombre;
	
	@NotNull
	@Past
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "fechanac")
	private LocalDate fechaNacimiento;
	
	@NotBlank	
	@Size(max = 15)
	private String celular;
	
	@NotEmpty
	@Email
	private String email;

}
