package com.ecampo.catalogo_producto.application.service;

import com.ecampo.catalogo_producto.domain.exception.CategoriaNotFoundException;
import com.ecampo.catalogo_producto.domain.exception.ProductoNotFoundException;
import com.ecampo.catalogo_producto.domain.model.Producto;
import com.ecampo.catalogo_producto.domain.ports.inbound.ConsultarCatalogoUseCase;
import com.ecampo.catalogo_producto.domain.ports.inbound.GestionarProductoUseCase;
import com.ecampo.catalogo_producto.domain.ports.outbound.ProductoRepositoryPort;

import java.util.List;

public class GestionarProductoService implements GestionarProductoUseCase, ConsultarCatalogoUseCase {

    private final ProductoRepositoryPort productoRepositoryPort;

    public GestionarProductoService(ProductoRepositoryPort productoRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
    }

    @Override
    public Producto obtenerPorId(Long id) {
        Producto producto=productoRepositoryPort.buscarPorId(id).orElseThrow(
                                ()-> new ProductoNotFoundException("El producto con Id "+id+" no existe")
                            );
        return producto;
    }

    @Override
    public List<Producto> obtenerTodos() {
        List<Producto>listaProducto=productoRepositoryPort.buscarTodos();
        return listaProducto;
    }

    @Override
    public List<Producto> obtenerPorCategoria(String nombreCategoria) {
        List<Producto>productos=productoRepositoryPort.buscarPorCategoria(nombreCategoria);
        if(productos.isEmpty()){
            throw new CategoriaNotFoundException("La categoria ingresada no existe");
        }
        return productos;
    }

    @Override
    public Producto crearProducto(Producto producto) {
        Producto nuevoProducto= new Producto(
                producto.getId(),
                producto.getSku(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecioBase(),
                producto.getStockDisponible(),
                producto.isActivo(),
                producto.getCategoriaId(),
                producto.getDescuentos()
        );
        return productoRepositoryPort.guardar(nuevoProducto);

    }

    @Override
    public Producto actualizarStock(Long id, int nuevoStock) {
        Producto datoProducto=productoRepositoryPort.buscarPorId(id)
                .orElseThrow(
                        ()-> new ProductoNotFoundException("Producto ingresado no existe")
                );
        datoProducto.cambiarStock(nuevoStock);
        return productoRepositoryPort.guardar(datoProducto);
    }
}
