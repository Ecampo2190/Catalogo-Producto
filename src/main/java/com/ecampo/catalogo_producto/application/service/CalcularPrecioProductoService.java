package com.ecampo.catalogo_producto.application.service;

import com.ecampo.catalogo_producto.domain.exception.ProductoNotFoundException;
import com.ecampo.catalogo_producto.domain.model.Producto;
import com.ecampo.catalogo_producto.domain.ports.inbound.CalcularPrecioProductoUseCase;
import com.ecampo.catalogo_producto.domain.ports.outbound.ProductoRepositoryPort;
import com.ecampo.catalogo_producto.domain.strategy.EstrategiaDescuento;
import com.ecampo.catalogo_producto.domain.strategy.EstrategiaDescuentoFactory;

public class CalcularPrecioProductoService implements CalcularPrecioProductoUseCase {

    private final ProductoRepositoryPort productoRepositoryPort;

    public CalcularPrecioProductoService(ProductoRepositoryPort productoRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
    }


    @Override
    public double calcularPrecioFinal(Long productoId,
                                      String tipoDescuento,
                                      double valorDescuento) {

        Producto producto = productoRepositoryPort.buscarPorId(productoId)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con el id: " + productoId));

        EstrategiaDescuento estrategia = EstrategiaDescuentoFactory.obtenerEstrategia(tipoDescuento);

        return producto.calcularPrecioFinal(estrategia, valorDescuento);
    }
}
