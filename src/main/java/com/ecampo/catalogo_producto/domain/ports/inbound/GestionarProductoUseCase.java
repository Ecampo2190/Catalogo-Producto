package com.ecampo.catalogo_producto.domain.ports.inbound;

import com.ecampo.catalogo_producto.domain.model.Producto;

public interface GestionarProductoUseCase {
    Producto crearProducto(Producto producto);
    Producto actualizarStock(Long id, int nuevoStock);
}
