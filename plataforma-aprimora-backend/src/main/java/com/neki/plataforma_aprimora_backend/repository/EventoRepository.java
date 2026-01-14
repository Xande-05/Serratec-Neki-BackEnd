package com.neki.plataforma_aprimora_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neki.plataforma_aprimora_backend.domain.Administrador;
import com.neki.plataforma_aprimora_backend.domain.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

	List<Evento> findByAdministrador(Administrador administrador);

	List<Evento> findByAdministradorId(Long adminId);
}
