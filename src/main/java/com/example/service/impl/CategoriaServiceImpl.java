package com.example.service.impl;

import com.example.model.Categoria;
import com.example.repository.CategoriaRepository;
import com.example.service.CategoriaService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private CategoriaRepository repositorio;

    public CategoriaServiceImpl(CategoriaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Categoria> obtenerTodasCategorias() {
        return repositorio.findAll();
    }

    @Override
    public List<Categoria> obtenerTodasCategoriasV2() {
        return repositorio.obtenerTodasCategoriasV2();
    }

    @Override
    public List<Categoria> obtenerTodasCategoriasV3() {
        return repositorio.obtenerTodasCategoriasV3();
    }

    @Override
    public Optional<Categoria> obtenerCategoriaById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public Categoria obtenerCategoriaByIdV2(Long id) {
        return repositorio.obtenerCategoriaByIdV2(id);
    }

    @Override
    public Categoria obtenerCategoriaByIdV3(Long id) {
        return repositorio.obtenerCategoriaByIdV3(id);
    }

    @Override
    public Optional<Categoria> obtenerCategoriaByNombre(String name) {
        return repositorio.findByNombre(name);
    }

    @Override
    public Categoria obtenerCategoriaByNombreV2(String name) {
        return repositorio.obtenerCategoriaByNombreV2(name);
    }

    @Override
    public List<Categoria> obtenerTodasCategoriasOrdenNombreDESC() {
        return repositorio.findAll(Sort.by(Sort.Direction.DESC, "nombre"));
    }

    @Override
    public List<Categoria> obtenerTodasCategoriasOrdenV2(Sort sort) {
        return repositorio.obtenerTodasCategoriasOrdenV2(sort);
    }

    @Override
    public Optional<Categoria> crearCategoria(Categoria categoria) {
        if(repositorio.existsByNombre(categoria.getNombre())){
            return Optional.empty();
        }
        return Optional.of(repositorio.save(categoria));
    }
}
