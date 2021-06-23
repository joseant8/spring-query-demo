package com.example.repository;

import com.example.model.Categoria;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNombre(String nombre);

    Boolean existsByNombre(String nombre);

    @Query("SELECT c FROM Categoria c")     // JPQL
    List<Categoria> obtenerTodasCategoriasV2();

    @Query(value = "SELECT * FROM categoria", nativeQuery = true)     // SQL
    List<Categoria> obtenerTodasCategoriasV3();

    @Query("SELECT c FROM Categoria c WHERE c.id = :idCat")     // JPQL
    Categoria obtenerCategoriaByIdV2(@Param("idCat") Long idCat);

    @Query(value = "SELECT * FROM categoria c WHERE c.id = :idCat", nativeQuery = true)     // SQL
    Categoria obtenerCategoriaByIdV3(@Param("idCat") Long idCat);

    @Query("SELECT c FROM Categoria c WHERE c.nombre = :name")
    Categoria obtenerCategoriaByNombreV2(@Param("name") String name);

    @Query("SELECT c FROM Categoria c")     // JPQL
    List<Categoria> obtenerTodasCategoriasOrdenV2(Sort sort);
}
