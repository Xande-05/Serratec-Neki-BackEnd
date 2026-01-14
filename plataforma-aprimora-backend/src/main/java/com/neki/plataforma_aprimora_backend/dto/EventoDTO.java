package com.neki.plataforma_aprimora_backend.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class EventoDTO {

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime data;
	
	private String nome;
	private String localizacao;
	private String imagemUrl;
	
	public EventoDTO() {
	}
	
	public EventoDTO(String nome, LocalDateTime data, String localizacao, String imagemUrl) {
		super();
		this.nome = nome;
		this.data = data;
		this.localizacao = localizacao;
		this.imagemUrl = imagemUrl;
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
	
}
