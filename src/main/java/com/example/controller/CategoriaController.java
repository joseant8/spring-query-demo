package com.example.controller;

import com.example.model.Categoria;
import com.example.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Obtener todas las categorías
     * @return lista de categorías
     */
    @GetMapping("/categorias")
    public List<Categoria> obtenerTodasCategorias(){
        return servicio.obtenerTodasCategorias();
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
