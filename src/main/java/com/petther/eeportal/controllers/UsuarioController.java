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
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private EventoRepository eventos;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody List<Usuario> listar() {
		return usuarios.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody ResponseEntity<Usuario> pesquisar(@PathVariable Long id){
		Optional<Usuario> usuario = usuarios.findById(id);
		
		if (usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Usuario inserir(@Validated @RequestBody Usuario usuario){
		return usuarios.save(usuario);
	}
	
	@GetMapping("/{idUsuario}/eventos")
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody ResponseEntity<Set<Evento>> listarEventos(@PathVariable Long idUsuario){
		Optional<Usuario> usuario = usuarios.findById(idUsuario);
		
		if (usuario.isPresent()) {
			usuario.get().setEventosUsuario(eventos.findEventosByIdUsuario(idUsuario));
			return ResponseEntity.ok(usuario.get().getEventosUsuario());
		}
		return ResponseEntity.notFound().build();
	}
}
