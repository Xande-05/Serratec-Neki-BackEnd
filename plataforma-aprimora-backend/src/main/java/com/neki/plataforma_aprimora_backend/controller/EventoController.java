package com.neki.plataforma_aprimora_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.neki.plataforma_aprimora_backend.domain.Evento;
import com.neki.plataforma_aprimora_backend.dto.AtualizarEventoDTO;
import com.neki.plataforma_aprimora_backend.dto.EventoDTO;
import com.neki.plataforma_aprimora_backend.service.EventoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/eventos")	
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    
    private Long getAdminIdFromRequest(HttpServletRequest request) {
        return (Long) request.getAttribute("adminId");
    }
    
    @PostMapping
    public ResponseEntity<?> criarEvento(
            @RequestBody EventoDTO dto,
            HttpServletRequest request) {
        try {
            Long adminId = getAdminIdFromRequest(request);
            
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
    public ResponseEntity<?> listarEventos(HttpServletRequest request) {
        try {
            Long adminId = getAdminIdFromRequest(request);
            
            List<Evento> eventos = eventoService.listarEventosPorAdmin(adminId);
            return ResponseEntity.ok(eventos);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEvento(
            @PathVariable Long id,
            HttpServletRequest request) {
        try {
            Long adminId = getAdminIdFromRequest(request);
            
            Evento evento = eventoService.buscarEventoPorId(id);
            
            if (!evento.getAdministrador().getId().equals(adminId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Você não tem permissão para acessar este evento");
            }
            
            return ResponseEntity.ok(evento);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
        }
    }
     
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEvento(
            @PathVariable Long id,
            @RequestBody AtualizarEventoDTO dto,
            HttpServletRequest request) {
        try {
            Long adminId = getAdminIdFromRequest(request);
            
            Evento evento = eventoService.buscarEventoPorId(id);
            
            if (!evento.getAdministrador().getId().equals(adminId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Você não tem permissão para editar este evento");
            }
            
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
    public ResponseEntity<?> deletarEvento(
            @PathVariable Long id,
            HttpServletRequest request) {
        try {
            Long adminId = getAdminIdFromRequest(request);
            
            Evento evento = eventoService.buscarEventoPorId(id);
            
            if (!evento.getAdministrador().getId().equals(adminId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Você não tem permissão para deletar este evento");
            }
            
            eventoService.deletarEvento(id);
            return ResponseEntity.ok("Evento deletado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}