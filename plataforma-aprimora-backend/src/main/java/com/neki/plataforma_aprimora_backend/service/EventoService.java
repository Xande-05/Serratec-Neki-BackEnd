package com.neki.plataforma_aprimora_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neki.plataforma_aprimora_backend.domain.Administrador;
import com.neki.plataforma_aprimora_backend.domain.Evento;
import com.neki.plataforma_aprimora_backend.repository.AdministradorRepository;
import com.neki.plataforma_aprimora_backend.repository.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	public Evento criarEvento(String nome, LocalDateTime data, String localizacao, String imagemUrl, Long adminId) {
		
		Optional <Administrador> adminOptional = administradorRepository.findById(adminId);
		
		if (adminOptional.isEmpty()) {
			throw new RuntimeException("Administrador não encontrado");
		}
		
		Administrador admin = adminOptional.get();
		
		Evento evento = new Evento();
        evento.setNome(nome);
        evento.setData(data);
        evento.setLocalizacao(localizacao);
        evento.setImagemUrl(imagemUrl);
        evento.setAdministrador(admin);
		
		return eventoRepository.save(evento);
	}
	
	public List<Evento> listarEventosPorAdmin(Long adminId) {
		return eventoRepository.findByAdministradorId(adminId);
	}
	
	public Evento atualizarEvento(Long eventoId, LocalDateTime novaData, String novaLocalizacao) {
		
		Optional <Evento> eventoOptional = eventoRepository.findById(eventoId);
		
		if (eventoOptional.isEmpty()) {
			throw new RuntimeException("Evento não encontrado");
		}
		
		Evento evento = eventoOptional.get();
		
		if (novaData != null) {
			evento.setData(novaData);
		}
		if (novaLocalizacao != null) {
			evento.setLocalizacao(novaLocalizacao);
		}
		
		return eventoRepository.save(evento);
	}
	
	public void deletarEvento(Long eventoId) {
		if (!eventoRepository.existsById(eventoId)) {
			throw new RuntimeException("Evento não encontrado");
		}
		
		eventoRepository.deleteById(eventoId);
	}
	
	public Evento buscarEventoPorId(Long eventoId) {
		return eventoRepository.findById(eventoId).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
	}
	
}
