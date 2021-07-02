package com.example.service;

import com.example.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    // ---------------------------------
    // Consultas select
    // ---------------------------------

    /**
     * Obtener usuario de la BD según su id
     * @param id id del usuario
     * @return usuario
     */
    Optional<Usuario> obtenerUsuarioById(Long id);


    /**
     * Obtener usuario de la BD según su nombre de usuario (username)
     * @param username username
     * @return usuario
     */
    Optional<Usuario> obtenerUsuarioByUsername(String username);


    /**
     * Obtener todos los usuarios de la BD
     * @return lista de usuarios
     */
    List<Usuario> obtenerTodosUsuarios();


    /**
     * Obtiene todos los usuarios que contengan el nombre indicado
     * @param nombre nombre
     * @return lista de usuarios
     */
    List<Usuario> obtenerUsuariosContieneNombre(String nombre);


    // ---------------------------------
    // Crear
    // ---------------------------------

    /**
     * Crear un nuevo usuario. Si ya existe un usuario con el mismo 'username' no se crea.
     * @param usuario nuevo usuario
     * @return usuario creado
     */
    Optional<Usuario> crearUsuario(Usuario usuario);


}
