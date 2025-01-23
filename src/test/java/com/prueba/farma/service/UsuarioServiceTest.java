package com.prueba.farma.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.prueba.farma.model.Usuario;
import com.prueba.farma.repository.UsuarioRepository;
import com.prueba.farma.util.UsuarioDTO;

public class UsuarioServiceTest {
    private final UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
    private final UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    @Test
    void testCrearUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        Usuario usuario = new Usuario();
        usuario.setNombres("Juan");
        usuario.setApellidos("Pérez");
        usuarioDTO.setNombres("Juan");
        usuarioDTO.setApellidos("Pérez");


        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = usuarioService.crearUsuario(usuarioDTO);
        assertEquals("Juan", resultado.getNombres());
    }

    @Test
    void testObtenerUsuarioPorId() {
        UUID id = UUID.randomUUID();
        Usuario usuario = new Usuario();
        usuario.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.obtenerUsuarioPorId(id);
        assertEquals(id, resultado.getId());
    }

}
