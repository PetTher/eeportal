package com.petther.eeportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petther.eeportal.models.Agenda;

public interface InscricaoRepository extends JpaRepository<Agenda, Long> {

	@Query(value = "select * from eeportal.agenda where id_evento = :idEvento and id_usuario = :idUsuario", nativeQuery = true)
	Agenda findByIdEventoByIdUsuario(@Param("idEvento") int idEvento, @Param("idUsuario") Long idUsuario);
}
