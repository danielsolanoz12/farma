package com.prueba.farma.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.prueba.farma.model.Usuario;
import com.prueba.farma.repository.UsuarioRepository;
import com.prueba.farma.util.UsuarioDTO;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(UsuarioDTO usuario) {
        Usuario user = new Usuario();
        if((usuario == null || usuario.getNombres() == null && usuario.getApellidos() == null && usuario.getFechaCreacion() == null && usuario.getFechaActualizacion() == null)) {
            throw new IllegalArgumentException("El DTO no puede estar vacío");
        }else{
            
            user.setNombres(usuario.getNombres());
            user.setApellidos(usuario.getApellidos());
            user.setFechaCreacion(usuario.getFechaCreacion());
            user.setFechaActualizacion(usuario.getFechaActualizacion());
        }
        return usuarioRepository.save(user);
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
