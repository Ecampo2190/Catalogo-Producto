package com.ecampo.catalogo_producto.domain.ports.inbound;

import java.math.BigDecimal;
import java.util.Map;

public interface ConsultarReportesCatalogoUseCase {
    int consultarStockDisponible(Long productoId);
    Map<String, BigDecimal> obtenerPrecioTotalPorCategoria(String nombreCategoria);
}
