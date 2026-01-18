package com.neki.plataforma_aprimora_backend.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "eventos")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private LocalDateTime data;
	
	@Column(nullable = false)
	private String localizacao;
	
	@Column(name = "imagem_url")
	private String imagemUrl;
	
	@ManyToOne
	@JoinColumn(name = "admin_id", nullable = false)
	private Administrador administrador;
	
	public Evento() {
	}
	
	public Evento(Long id, String nome, LocalDateTime data, String localizacao, String imagemUrl,
			Administrador administrador) {
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.localizacao = localizacao;
		this.imagemUrl = imagemUrl;
		this.administrador = administrador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getData() {
	    return data;
	}

	public void setData(LocalDateTime data) {
	    this.data = data;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}
	
	public Administrador getAdministrador() {
		return administrador;
	}
	
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
}
