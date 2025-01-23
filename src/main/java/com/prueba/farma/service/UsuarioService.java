package com.prueba.farma.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.prueba.farma.model.Usuario;
import com.prueba.farma.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario actualizarUsuario (UUID id, Usuario usuario){
        Usuario usuarioActualizar = usuarioRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("El usuario que desea Actualizar no existe"));

        usuarioActualizar.setNombres(usuario.getNombres());
        usuarioActualizar.setApellidos(usuario.getApellidos());
        return usuarioRepository.save(usuarioActualizar);

    }

    public Usuario obtenerUsuarioPorId(UUID id){

        return usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("El usuario no existe"));
    }


}
