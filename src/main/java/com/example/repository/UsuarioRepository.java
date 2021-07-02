package com.example.repository;

import com.example.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("SELECT u FROM Usuario u WHERE u.nombreCompleto LIKE %:nombre% ")
    List<Usuario> obtenerUsuariosContieneNombre(@Param("nombre") String nombre);

}
