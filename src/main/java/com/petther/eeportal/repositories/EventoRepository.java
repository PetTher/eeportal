package com.petther.eeportal.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petther.eeportal.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

	@Query(value = "select e.id_evento, e.usuario_inclusao, e.nome_evento, e.data_evento, e.hora_evento, e.endereco_evento from eeportal.evento e inner join eeportal.agenda a on e.id_evento = a.id_evento where a.id_usuario = :id", nativeQuery = true)
	Set<Evento> findEventosByIdUsuario(@Param("id") Long idUsuario);
}
