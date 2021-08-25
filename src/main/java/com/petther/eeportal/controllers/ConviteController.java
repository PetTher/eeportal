package com.petther.eeportal.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.petther.eeportal.models.Convite;
import com.petther.eeportal.models.Evento;
import com.petther.eeportal.repositories.ConviteRepository;
import com.petther.eeportal.repositories.EventoRepository;

@Controller
@RequestMapping("/convite")
public class ConviteController {

	@Autowired
	private ConviteRepository convites;
	
	@Autowired
	private EventoRepository eventos;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public @ResponseBody void convidar(@Validated @RequestBody Convite convite){
		convites.save(convite);
	}

	@GetMapping("/{idUsuario}")
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody ResponseEntity<Set<Evento>> listarConvites(@PathVariable Long idUsuario){
		return ResponseEntity.ok(eventos.findEventosByIdUsuario(idUsuario));
	}
	
	@PatchMapping("/{idConvite}")
	@ResponseStatus(code = HttpStatus.OK)
	public void responderConvite(@PathVariable Long idConvite, @RequestBody short statusConvite) {
		if(!convites.existsById(idConvite)) {
			ResponseEntity.notFound().build();
		}
		
		Convite convite = convites.findById(idConvite).get();
		convite.setStatusConvite(statusConvite);
		convites.save(convite);
	}
}
