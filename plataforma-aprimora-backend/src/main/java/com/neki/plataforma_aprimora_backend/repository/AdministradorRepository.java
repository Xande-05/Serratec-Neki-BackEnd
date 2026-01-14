package com.neki.plataforma_aprimora_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neki.plataforma_aprimora_backend.domain.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

	Optional <Administrador> findByEmail(String email);
	
	boolean existsByEmail(String email);
}
