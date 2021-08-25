package com.petther.eeportal.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petther.eeportal.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "select u.id_usuario, u.nome_completo, u.apelido, u.email, u.senha from eeportal.usuario u inner join eeportal.agenda a on u.id_usuario = a.id_usuario where a.id_evento = :id", nativeQuery = true)
	Set<Usuario> findUsuariosByIdEvento(@Param("id") int idEvento);
}
