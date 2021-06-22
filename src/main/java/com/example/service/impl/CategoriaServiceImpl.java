package com.example.service.impl;

import com.example.model.Categoria;
import com.example.repository.CategoriaRepository;
import com.example.service.CategoriaService;
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
    public Optional<Categoria> obtenerCategoriaById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public Optional<Categoria> crearCategoria(Categoria categoria) {
        if(repositorio.existsByNombre(categoria.getNombre())){
            return Optional.empty();
        }
        return Optional.of(repositorio.save(categoria));
    }
}
