package com.ecampo.catalogo_producto.domain.ports.inbound;

public interface CalcularPrecioProductoUseCase {
    double calcularPrecioFinal(Long productoId, String tipoDescuento, double valorDescuento);
}
