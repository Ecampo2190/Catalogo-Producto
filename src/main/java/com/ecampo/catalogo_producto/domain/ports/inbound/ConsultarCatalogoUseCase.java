package com.ecampo.catalogo_producto.domain.ports.inbound;

import com.ecampo.catalogo_producto.domain.model.Producto;

import java.util.List;

public interface ConsultarCatalogoUseCase {
    Producto obtenerPorId(Long id);
    List<Producto> obtenerTodos();
    List<Producto> obtenerPorCategoria(String nombreCategoria);
}
