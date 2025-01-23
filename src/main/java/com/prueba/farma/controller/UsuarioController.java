package com.prueba.farma.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.farma.model.Usuario;
import com.prueba.farma.service.UsuarioService;
import com.prueba.farma.util.UsuarioDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * Controlador REST para gestionar los usuarios.
 * 
 * @author [Daniel Solano]
 * @version 1.0
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Crear un nuevo usuario.
     *
     * @param usuario Objeto del nuevo usuario a crear.
     * @return El usuario creado con su ID asignado.
     */
    @Operation(
        summary = "Crear usuario",
        description = "Crea un nuevo usuario en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "400", description = "Datos del usuario no válidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    /**
     * Actualizar un usuario existente.
     *
     * @param id      Identificador del usuario a actualizar.
     * @param usuario Objeto del usuario con los nuevos datos.
     * @return El usuario actualizado.
     */
    @Operation(
        summary = "Actualizar usuario",
        description = "Actualiza los datos de un usuario existente."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos de actualización no válidos", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable UUID id, @Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
    }

    /**
     * Obtiene una lista de todos los usuarios.
     *
     * @return Lista de usuarios.
     */
    @Operation(
        summary = "Obtener usuarios",
        description = "Devuelve una lista con todos los usuarios registrados en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))
    })
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerUsuarios());
    }

     /**
     * Obtiene un usuario por su ID.
     *
     * @param id Identificador único del usuario.
     * @return El usuario correspondiente al ID proporcionado.
     */
    @Operation(
        summary = "Obtener usuario por ID",
        description = "Obtiene los datos de un usuario utilizando su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

}
