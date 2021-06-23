package com.example.service;

import com.example.model.Categoria;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    // ---------------------------------
    // Consultas select
    // ---------------------------------

    /**
     * Obtiene todas las categorías de la BD
     * @return lista de categorías
     */
    public List<Categoria> obtenerTodasCategorias();

    /**
     * Obtiene todas las categorías de la BD (v2 JPQL)
     * @return lista de categorías
     */
    public List<Categoria> obtenerTodasCategoriasV2();

    /**
     * Obtiene todas las categorías de la BD (v3 SQL)
     * @return lista de categorías
     */
    public List<Categoria> obtenerTodasCategoriasV3();

    /**
     * Obtiene una categoría según su id
     * @param id
     * @return categoría
     */
    public Optional<Categoria> obtenerCategoriaById(Long id);

    /**
     * Obtiene una categoría según su id (v2 JPQL)
     * @param id
     * @return categoría
     */
    public Categoria obtenerCategoriaByIdV2(Long id);

    /**
     * Obtiene una categoría según su id (v3 SQL)
     * @param id
     * @return categoría
     */
    public Categoria obtenerCategoriaByIdV3(Long id);


    /**
     * Obtiene una categoría según su nombre
     * @param name
     * @return categoria
     */
    public Optional<Categoria> obtenerCategoriaByNombre(String name);

    /**
     * Obtiene una categoría según su nombre (v2 JPQL)
     * @param name
     * @return categoria
     */
    public Categoria obtenerCategoriaByNombreV2(String name);


    // ---------------------------------
    // Consultas select ordenadas
    // ---------------------------------

    /**
     * Obtiene todas las categorías ordenadas por nombre DESC
     * @return lista de categorías
     */
    public List<Categoria> obtenerTodasCategoriasOrdenNombreDESC();

    /**
     * Obtiene todas las categorías ordenadas por nombre DESC (V2 JPQL)
     * @return lista de categorías
     */
    public List<Categoria> obtenerTodasCategoriasOrdenV2(Sort sort);


    // ---------------------------------
    // Crear
    // ---------------------------------

    /**
     * Crea una nueva categoría en la BD. Si ya existe un categoría con el mismo 'nombre' no se crea.
     * @param categoria
     * @return categoría creada
     */
    public Optional<Categoria> crearCategoria(Categoria categoria);
}
