package com.ecampo.catalogo_producto.domain.ports.inbound;

import com.ecampo.catalogo_producto.domain.model.Categoria;

import java.util.List;
import java.util.Map;

public interface ObtenerCategoriaUseCase {

    List<Categoria> listarCategoria();
    Categoria obtenerCategoriaPorId(Long id);
}
