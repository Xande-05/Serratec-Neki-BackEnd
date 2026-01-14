package com.neki.plataforma_aprimora_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neki.plataforma_aprimora_backend.config.JwtUtil;
import com.neki.plataforma_aprimora_backend.domain.Evento;
import com.neki.plataforma_aprimora_backend.dto.AtualizarEventoDTO;
import com.neki.plataforma_aprimora_backend.dto.EventoDTO;
import com.neki.plataforma_aprimora_backend.service.EventoService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/eventos")	
@CrossOrigin(origins = "*")
public class EventoController {

	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	 @PostMapping
	    public ResponseEntity<?> criarEvento(
	            @RequestBody EventoDTO dto,
	            @RequestParam Long adminId) {
	        try {
	            Evento evento = eventoService.criarEvento(
	                dto.getNome(),
	                dto.getData(),
	                dto.getLocalizacao(),
	                dto.getImagemUrl(),
	                adminId
	            );
	            
	            return ResponseEntity.status(HttpStatus.CREATED).body(evento);
	            
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	
	 @GetMapping
	    public ResponseEntity<?> listarEventos(@RequestParam Long adminId) {
	        try {
	            List<Evento> eventos = eventoService.listarEventosPorAdmin(adminId);
	            return ResponseEntity.ok(eventos);
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	
	 @GetMapping("/{id}")
	    public ResponseEntity<?> buscarEvento(@PathVariable Long id) {
	        try {
	            Evento evento = eventoService.buscarEventoPorId(id);
	            return ResponseEntity.ok(evento);
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(e.getMessage());
	        }
	    }
	 
	 @PutMapping("/{id}")
	    public ResponseEntity<?> atualizarEvento(
	            @PathVariable Long id,
	            @RequestBody AtualizarEventoDTO dto) {
	        try {
	            Evento eventoAtualizado = eventoService.atualizarEvento(
	                id,
	                dto.getData(),
	                dto.getLocalizacao()
	            );
	            
	            return ResponseEntity.ok(eventoAtualizado);
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	
	 @DeleteMapping("/{id}")
	    public ResponseEntity<?> deletarEvento(@PathVariable Long id) {
	        try {
	            eventoService.deletarEvento(id);
	            return ResponseEntity.ok("Evento deletado com sucesso!");
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
}
