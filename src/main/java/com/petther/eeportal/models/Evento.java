package com.petther.eeportal.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "evento", schema = "eeportal")
public class Evento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_evento")
	private int idEvento;
	
	private Long usuarioInclusao;
	
	private String nomeEvento;
	
	
	private String enderecoEvento;

	@Column(name = "data_evento", columnDefinition = "DATE")
	private LocalDate dataEvento;
	
	@Column(name = "hora_evento", columnDefinition = "TIME")
	private LocalTime horaEvento;
	
	@ManyToMany
	@JsonIgnore
	@JoinTable(
			name = "agenda",
			joinColumns = @JoinColumn(name = "id_evento"),
			inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	private Set<Usuario> inscritos;

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public Long getUsuarioInclusao() {
		return usuarioInclusao;
	}

	public void setUsuarioInclusao(Long usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public LocalDate getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(LocalDate dataEvento) {
		this.dataEvento = dataEvento;
	}

	public LocalTime getHoraEvento() {
		return horaEvento;
	}

	public void setHoraEvento(LocalTime horaEvento) {
		this.horaEvento = horaEvento;
	}

	public String getEnderecoEvento() {
		return enderecoEvento;
	}

	public void setEnderecoEvento(String enderecoEvento) {
		this.enderecoEvento = enderecoEvento;
	}

	public Set<Usuario> getInscritos() {
		return inscritos;
	}

	public void setInscritos(Set<Usuario> inscritos) {
		this.inscritos = inscritos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEvento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return idEvento == other.idEvento;
	}
	
}
