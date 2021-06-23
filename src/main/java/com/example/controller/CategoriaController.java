package com.example.controller;

import com.example.model.Categoria;
import com.example.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/API")
public class CategoriaController {

    @Autowired
    private CategoriaService servicio;

    //https://www.baeldung.com/spring-data-jpa-query

    // --------------------------------------------
    // Obtener datos - consultas select
    // --------------------------------------------

    /**
     * Obtener todas las categorías
     * @return lista de categorías
     */
    @GetMapping("/categorias")
    public List<Categoria> obtenerTodasCategorias(){
        return servicio.obtenerTodasCategorias();
    }

    /**
     * Obtener todas las categorías (versión 2 con JPQL)
     * @return lista de categorías
     */
    @GetMapping("/categorias-v2")
    public List<Categoria> obtenerTodasCategoriasV2(){
        return servicio.obtenerTodasCategoriasV2();
    }

    /**
     * Obtener todas las categorías (versión 3 con SQL)
     * @return lista de categorías
     */
    @GetMapping("/categorias-v3")
    public List<Categoria> obtenerTodasCategoriasV3(){
        return servicio.obtenerTodasCategoriasV3();
    }


    /**
     * Obtener una categoría según el id
     * @param id
     * @return
     */
    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaById(@PathVariable Long id){
        Optional<Categoria> categoria = servicio.obtenerCategoriaById(id);
        if(categoria.isPresent()){
            return ResponseEntity.ok().body(categoria.get());
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Obtener una categoría según el id (versión 2 con JPQL)
     * @param id
     * @return
     */
    @GetMapping("/categorias/v2/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaByIdV2(@PathVariable Long id){
        Categoria categoria = servicio.obtenerCategoriaByIdV2(id);
        if(categoria != null){
            return ResponseEntity.ok().body(categoria);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtener una categoría según el id (versión 3 con SQL)
     * @param id
     * @return
     */
    @GetMapping("/categorias/v3/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaByIdV3(@PathVariable Long id){
        Categoria categoria = servicio.obtenerCategoriaByIdV3(id);
        if(categoria != null){
            return ResponseEntity.ok().body(categoria);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Obtener una categoría según nombre
     * @param nombre
     * @return
     */
    @GetMapping("/categorias/nombre/{nombre}")
    public ResponseEntity<Categoria> obtenerCategoriaByNombre(@PathVariable String nombre){
        Optional<Categoria> categoria = servicio.obtenerCategoriaByNombre(nombre);
        if(categoria.isPresent()){
            return ResponseEntity.ok().body(categoria.get());
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtener una categoría según nombre (versión 2 con JPQL)
     * @param nombre
     * @return
     */
    @GetMapping("/categorias/nombre/v2/{nombre}")
    public ResponseEntity<Categoria> obtenerCategoriaByNombreV2(@PathVariable String nombre){
        Categoria categoria = servicio.obtenerCategoriaByNombreV2(nombre);
        if(categoria != null){
            return ResponseEntity.ok().body(categoria);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // --------------------------------------------
    // Obtener datos ordenados
    // --------------------------------------------

    /**
     * Obtener todas las categorías ordenadas por nombre DESC
     * @return lista de categorías
     */
    @GetMapping("/categorias/orden/nombre-desc")
    public List<Categoria> obtenerTodasCategoriasOrdenNombreDESC(){
        return servicio.obtenerTodasCategoriasOrdenNombreDESC();
    }

    /**
     * Obtener todas las categorías ordenadas por nombre DESC (v2 JPQL)
     * @return lista de categorías
     */
    @GetMapping("/categorias/orden/v2/nombre-desc")
    public List<Categoria> obtenerTodasCategoriasOrdenNombreDESCv2(){
        return servicio.obtenerTodasCategoriasOrdenV2(Sort.by(Sort.Direction.DESC, "nombre"));
    }

    /**
     * Obtener todas las categorías ordenadas según params (v2 JPQL)
     * @return lista de categorías
     */
    @GetMapping("/categorias/orden/v2")
    public List<Categoria> obtenerTodasCategoriasOrdenadas(@RequestParam(name="id", required = false) String id,
                                                            @RequestParam(name="nombre", required = false) String nombre){

        if(id != null){
            if(id.equals("ASC")){
                return servicio.obtenerTodasCategoriasOrdenV2(Sort.by(Sort.Direction.ASC, "id"));
            }else{
                return servicio.obtenerTodasCategoriasOrdenV2(Sort.by(Sort.Direction.DESC, "id"));
            }
        }else if(nombre != null){
            if(nombre.equals("ASC")){
                return servicio.obtenerTodasCategoriasOrdenV2(Sort.by(Sort.Direction.ASC, "nombre"));
            }else{
                return servicio.obtenerTodasCategoriasOrdenV2(Sort.by(Sort.Direction.DESC, "nombre"));
            }
        }

        // Si no se pasa ningún parámetro se ordena según el id
        return servicio.obtenerTodasCategoriasOrdenV2(Sort.by(Sort.Direction.ASC, "id"));
    }



    // --------------------------------------------
    // Crear
    // --------------------------------------------


    /**
     * Crea una nueva categoría en la BD
     * @param categoria
     * @return la nueva categoría creada
     * @throws URISyntaxException
     */
    @PostMapping("/categorias")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) throws URISyntaxException {
        if(categoria.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Categoria> categoriaCreada = servicio.crearCategoria(categoria);
        if(categoriaCreada.isPresent()){
            return ResponseEntity.created(new URI("API/categorias/" + categoriaCreada.get().getId())).body(categoriaCreada.get());
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);   // ya existe una categoría con el nombre indicado
        }

    }




}
