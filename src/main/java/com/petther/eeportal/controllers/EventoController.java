package com.petther.eeportal.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.petther.eeportal.models.Evento;
import com.petther.eeportal.models.Usuario;
import com.petther.eeportal.repositories.EventoRepository;
import com.petther.eeportal.repositories.UsuarioRepository;

@Controller
@RequestMapping("/evento")
public class EventoController {

	@Autowired
	private EventoRepository eventos;
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody List<Evento> listar(){
		return eventos.findAll();
	}
	
	@GetMapping("/id")
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody ResponseEntity<Evento> pesquisar(@PathVariable int id) {
		Optional<Evento> evento = eventos.findById(id);
		
		if (evento.isPresent()) {
			return ResponseEntity.ok(evento.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public @ResponseBody Evento inserir(@Validated @RequestBody Evento evento){
		return eventos.save(evento);
	}
	
	@GetMapping("/{idEvento}/usuarios")
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody ResponseEntity<Set<Usuario>> listarUsuarios(@PathVariable int idEvento){
		Optional<Evento> evento = eventos.findById(idEvento);
		
		if (evento.isPresent()) {
			evento.get().setInscritos(usuarios.findUsuariosByIdEvento(idEvento));
			return ResponseEntity.ok(evento.get().getInscritos());
		}
		return ResponseEntity.notFound().build();
	}

}
