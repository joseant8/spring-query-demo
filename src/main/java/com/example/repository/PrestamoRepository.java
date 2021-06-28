package com.example.repository;

import com.example.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    @Query("SELECT p FROM Prestamo p WHERE p.usuario.id = :id")
    List<Prestamo> obtenerPrestamosDeUsuario(@Param("id") Long id);

    @Query("SELECT p FROM Prestamo p WHERE p.cuentaIngreso.id IN (SELECT c.id FROM Cuenta c JOIN c.usuarios u WHERE u.id=:idUsuario)")
    List<Prestamo> obtenerPrestamosCuentasDeUsuario(@Param("idUsuario") Long idUsuario);

}
