package com.neki.plataforma_aprimora_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.neki.plataforma_aprimora_backend.domain.Administrador;
import com.neki.plataforma_aprimora_backend.repository.AdministradorRepository;

@Service
public class AuthService {

	@Autowired
	private AdministradorRepository administradorRepository;
	
	 private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	 
	 public Administrador cadastrar(String nome, String email, String senha) {
		 
		 if(administradorRepository.existsByEmail(email)) {
			 throw new RuntimeException("Email já cadastrado");
		 }
		 
		 String senhaCriptografada = passwordEncoder.encode(senha);
		 
			Administrador admin = new Administrador();
			admin.setNome(nome);
			admin.setEmail(email);
			admin.setSenha(senhaCriptografada);
			
			return administradorRepository.save(admin);
	 }
	 
	 public Administrador login(String email, String senha) {
		 
		 Optional<Administrador> adminOptional = administradorRepository.findByEmail(email);
		 
		 if (adminOptional.isEmpty()) {
			 throw new RuntimeException("Administrador não encontrado");
		 }
		 
		 Administrador admin = adminOptional.get();
		 
		 if (!passwordEncoder.matches(senha, admin.getSenha())) {
			 throw new RuntimeException("Email ou senha inválidos");
		 }
		 
		 return admin;
	 }
}
