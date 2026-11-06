package com.ecampo.catalogo_producto.infraestructure.config;

import com.ecampo.catalogo_producto.application.service.CalcularPrecioProductoService;
import com.ecampo.catalogo_producto.application.service.CategoriaService;
import com.ecampo.catalogo_producto.application.service.GestionarProductoService;
import com.ecampo.catalogo_producto.application.service.ReporteCatalogoService;
import com.ecampo.catalogo_producto.domain.ports.inbound.*;
import com.ecampo.catalogo_producto.domain.ports.outbound.CategoriaRepositoryPort;
import com.ecampo.catalogo_producto.domain.ports.outbound.ProductoRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ObtenerCategoriaUseCase obtenerCategoriaUseCase(CategoriaRepositoryPort categoriaRepositoryPort) {
        return new CategoriaService(categoriaRepositoryPort);
    }

    @Bean
    public GestionarProductoUseCase gestionarProductoUseCase(ProductoRepositoryPort productoRepositoryPort){
        return new GestionarProductoService(productoRepositoryPort);
    }

    @Bean
    public ConsultarReportesCatalogoUseCase consultarReportesCatalogoUseCase(ProductoRepositoryPort productoRepositoryPort){
        return new ReporteCatalogoService(productoRepositoryPort);
    }

    @Bean
    public ConsultarCatalogoUseCase consultarCatalogoUseCase(ProductoRepositoryPort productoRepositoryPort) {
        return new GestionarProductoService(productoRepositoryPort);
    }

    @Bean
    public CalcularPrecioProductoUseCase calcularPrecioProductoUseCase(ProductoRepositoryPort productoRepositoryPort) {
        return new CalcularPrecioProductoService(productoRepositoryPort);
    }
}
