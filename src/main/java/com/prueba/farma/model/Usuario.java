package com.prueba.farma.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue
     private UUID id;

    @Column(nullable=false)
    private String nombres;

    @Column(nullable=false)
    private String apellidos;

    @Column(nullable=false)
    private LocalDateTime fechaCreacion;

    @Column(nullable=false)
    private LocalDateTime fechaActualizacion;


    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

}
