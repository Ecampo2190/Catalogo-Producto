package com.ecampo.catalogo_producto.domain.ports.outbound;

import com.ecampo.catalogo_producto.domain.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepositoryPort {

    Categoria guardar(Categoria categoria);
    Optional<Categoria> buscarPorId(Long id);
    Optional<Categoria> buscarPorNombre(String nombre);
    List<Categoria> buscarTodas();

}
