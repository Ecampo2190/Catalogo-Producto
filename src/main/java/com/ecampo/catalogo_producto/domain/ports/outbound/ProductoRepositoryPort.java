package com.ecampo.catalogo_producto.domain.ports.outbound;

import com.ecampo.catalogo_producto.domain.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoRepositoryPort {

    Producto  guardar(Producto producto);
    Optional <Producto> buscarPorId(Long id);
    List<Producto> buscarTodos();
    List<Producto> buscarPorCategoria(String nombreCategoria);
}
