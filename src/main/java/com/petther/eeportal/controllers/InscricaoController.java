package com.petther.eeportal.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.petther.eeportal.models.Agenda;
import com.petther.eeportal.repositories.InscricaoRepository;

@Controller
@RequestMapping("/agenda")
public class InscricaoController {

	@Autowired
	private InscricaoRepository inscricoes;
	
	@PostMapping("/inscrever")
	@ResponseStatus(code = HttpStatus.CREATED)
	public @ResponseBody void inscrever(@Validated @RequestBody Agenda inscricao) {
		inscricoes.save(inscricao);
	}

	@DeleteMapping("/{idEvento}/{idUsuario}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void desinscrever(@PathVariable int idEvento, @PathVariable Long idUsuario){
		Optional<Agenda> inscricao = Optional.ofNullable(inscricoes.findByIdEventoByIdUsuario(idEvento, idUsuario));
		
		if(inscricao.isPresent()) {
			inscricoes.deleteById(inscricao.get().getIdAgenda());
		}
	}

}
