package com.petther.eeportal.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "convite", schema = "eeportal")
public class Convite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_convite")
	private Long idConvite;
	
	@Column(name="id_anfitriao")
	private Long idAnfitriao;
	
	@Column(name="id_convidado")
	private Long idConvidado;
	
	@Column(name="id_evento")
	private int idEvento;
	
	@Column(name="data_convite")
	private Timestamp dataConvite;
	
	@Column(name="status_convite")
	private short statusConvite;

	public Long getIdConvite() {
		return idConvite;
	}

	public void setIdConvite(Long idConvite) {
		this.idConvite = idConvite;
	}

	public Long getIdAnfitriao() {
		return idAnfitriao;
	}

	public void setIdAnfitriao(Long idAnfitriao) {
		this.idAnfitriao = idAnfitriao;
	}

	public Long getIdConvidado() {
		return idConvidado;
	}

	public void setIdConvidado(Long idConvidado) {
		this.idConvidado = idConvidado;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public Timestamp getDataConvite() {
		return dataConvite;
	}

	public void setDataConvite(Timestamp dataConvite) {
		this.dataConvite = dataConvite;
	}

	public short getStatusConvite() {
		return statusConvite;
	}

	public void setStatusConvite(short statusConvite) {
		this.statusConvite = statusConvite;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idConvite);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Convite other = (Convite) obj;
		return Objects.equals(idConvite, other.idConvite);
	}
}
