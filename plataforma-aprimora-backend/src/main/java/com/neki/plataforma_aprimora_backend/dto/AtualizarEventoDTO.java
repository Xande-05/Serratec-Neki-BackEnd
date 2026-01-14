package com.neki.plataforma_aprimora_backend.dto;

import java.time.LocalDateTime;

public class AtualizarEventoDTO {

	private LocalDateTime data;
	private String localizacao;
	
	
	public AtualizarEventoDTO(){
	}

	public AtualizarEventoDTO(LocalDateTime data, String localizacao) {
		this.data = data;
		this.localizacao = localizacao;
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
	
	
}
