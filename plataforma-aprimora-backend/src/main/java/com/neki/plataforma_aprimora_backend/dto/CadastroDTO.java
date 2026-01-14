package com.neki.plataforma_aprimora_backend.dto;

public class CadastroDTO {

	private String nome;
	private String email;
	private String senha;
	private String confirmarSenha;
	
	public CadastroDTO() {
	}
	
	public CadastroDTO(String nome, String email, String senha, String confirmarSenha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.confirmarSenha = confirmarSenha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
	
}
