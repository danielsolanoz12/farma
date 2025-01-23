package com.prueba.farma.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.farma.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, UUID> {

}
