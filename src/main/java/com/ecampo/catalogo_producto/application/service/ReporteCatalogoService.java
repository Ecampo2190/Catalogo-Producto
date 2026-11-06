package com.ecampo.catalogo_producto.application.service;

import com.ecampo.catalogo_producto.domain.exception.ProductoNotFoundException;
import com.ecampo.catalogo_producto.domain.model.Producto;
import com.ecampo.catalogo_producto.domain.ports.inbound.ConsultarReportesCatalogoUseCase;
import com.ecampo.catalogo_producto.domain.ports.outbound.ProductoRepositoryPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReporteCatalogoService implements ConsultarReportesCatalogoUseCase {

    private final ProductoRepositoryPort productoRepositoryPort;


    public ReporteCatalogoService(ProductoRepositoryPort productoRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
    }

    @Override
    public int consultarStockDisponible(Long productoId) {
        Producto producto=productoRepositoryPort.buscarPorId(productoId).orElseThrow(
                ()->new ProductoNotFoundException("El id "+productoId+" del producto ingresado no existe")
        );

        return producto.getStockDisponible();
    }

    @Override
    public Map<String, BigDecimal> obtenerPrecioTotalPorCategoria(String nombreCategoria) {

        List<Producto> productos=productoRepositoryPort.buscarPorCategoria(nombreCategoria);
        /*double totalAcumulado = productos.stream()
                .mapToDouble(x -> x.getPrecioBase() * x.getStockDisponible())
                .sum();

        BigDecimal totalSinNotacion = BigDecimal.valueOf(totalAcumulado);*/
        BigDecimal totalAcumulado = productos.stream()
                .map(p -> BigDecimal.valueOf(p.getPrecioBase())
                        .multiply(BigDecimal.valueOf(p.getStockDisponible())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Map.of(nombreCategoria, totalAcumulado);


    }
}
