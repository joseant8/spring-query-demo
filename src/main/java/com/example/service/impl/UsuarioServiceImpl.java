package com.example.service.impl;

import com.example.model.Usuario;
import com.example.repository.UsuarioRepository;
import com.example.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    UsuarioRepository usuarioRepositorio;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Optional<Usuario> obtenerUsuarioById(Long id) {
        return usuarioRepositorio.findById(id);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioByUsername(String username) {
        return usuarioRepositorio.findByUsername(username);
    }

    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Optional<Usuario> crearUsuario(Usuario usuario) {
        // miramos si ya existe un usuario con el 'username' indicado
        if(usuarioRepositorio.existsByUsername(usuario.getUsername())){
            return Optional.empty();
        }else{
            Usuario usuarioCreado = usuarioRepositorio.save(usuario);
            return Optional.of(usuarioCreado);
        }
    }
}
