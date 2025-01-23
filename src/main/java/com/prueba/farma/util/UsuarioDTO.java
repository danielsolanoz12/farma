package com.prueba.farma.util;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

public class UsuarioDTO {

    @Column(nullable=false)
    private String nombres;

    @Column(nullable=false)
    private String apellidos;

    @Column(nullable=false)
    private LocalDateTime fechaCreacion;

    @Column(nullable=false)
    private LocalDateTime fechaActualizacion;

    public String getNombres() {
        return nombres;
    }

    public UsuarioDTO() {
    }


    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    
}
