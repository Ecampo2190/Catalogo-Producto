package com.ecampo.catalogo_producto.application.service;

import com.ecampo.catalogo_producto.domain.exception.CategoriaNotFoundException;
import com.ecampo.catalogo_producto.domain.model.Categoria;
import com.ecampo.catalogo_producto.domain.ports.inbound.ObtenerCategoriaUseCase;
import com.ecampo.catalogo_producto.domain.ports.outbound.CategoriaRepositoryPort;

import java.util.List;

public class CategoriaService implements ObtenerCategoriaUseCase {

    private final CategoriaRepositoryPort categoriaRepositoryPort;

    public CategoriaService(CategoriaRepositoryPort categoriaRepositoryPort) {
        this.categoriaRepositoryPort = categoriaRepositoryPort;
    }


    @Override
    public List<Categoria> listarCategoria() {

        return categoriaRepositoryPort.buscarTodas();
    }

    @Override
    public Categoria obtenerCategoriaPorId(Long id) {
        Categoria categoria=categoriaRepositoryPort.buscarPorId(id).orElseThrow(
                ()->new CategoriaNotFoundException("La categoria con id "+id+" no existe")
        );
        return categoria;
    }
}
