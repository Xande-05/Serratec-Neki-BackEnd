package com.neki.plataforma_aprimora_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neki.plataforma_aprimora_backend.config.JwtUtil;
import com.neki.plataforma_aprimora_backend.domain.Administrador;
import com.neki.plataforma_aprimora_backend.dto.AdminResponseDTO;
import com.neki.plataforma_aprimora_backend.dto.CadastroDTO;
import com.neki.plataforma_aprimora_backend.dto.LoginDTO;
import com.neki.plataforma_aprimora_backend.dto.LoginResponseDTO;
import com.neki.plataforma_aprimora_backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(@RequestBody CadastroDTO dto) {
		try {
			if (!dto.getSenha().equals(dto.getConfirmarSenha())) {
				return ResponseEntity.badRequest().body("As senhas não coincidem!");
			}
			
			Administrador admin = authService.cadastrar(
					dto.getNome(),
					dto.getEmail(),
					dto.getSenha()
			);
			
			AdminResponseDTO response = new AdminResponseDTO(
					admin.getId(),
					admin.getNome(),
					admin.getEmail()
			);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
			
		} catch(RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
		try {
			Administrador admin = authService.login(dto.getEmail(), dto.getSenha());
			
			String token = jwtUtil.gerarToken(admin.getId(), admin.getEmail());
			
			LoginResponseDTO response = new LoginResponseDTO(
		            admin.getId(),
		            admin.getNome(),
		            admin.getEmail(),
		            token
		        );
			
			return ResponseEntity.ok(response);
		
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(e.getMessage());
		}
	}
}
