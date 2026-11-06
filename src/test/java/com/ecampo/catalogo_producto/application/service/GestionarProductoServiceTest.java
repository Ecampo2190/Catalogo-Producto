package com.ecampo.catalogo_producto.application.service;

import com.ecampo.catalogo_producto.domain.exception.CategoriaNotFoundException;
import com.ecampo.catalogo_producto.domain.exception.ProductoNotFoundException;
import com.ecampo.catalogo_producto.domain.model.Descuento;
import com.ecampo.catalogo_producto.domain.model.Producto;
import com.ecampo.catalogo_producto.domain.model.TipoDescuento;
import com.ecampo.catalogo_producto.domain.ports.outbound.ProductoRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GestionarProductoServiceTest {
    @Mock
    private ProductoRepositoryPort productoRepositoryPort; // El puerto (doble de riesgo)

    @InjectMocks
    private GestionarProductoService gestionarProductoService; // La implementación del caso de uso


    @Test
    @DisplayName("Debe actualizar el stock del producto exitosamente cuando el ID existe")
    void actualizarStockExitosamente() {
        // Arrange (Preparar datos y simular el puerto)
        Long id = 1L;
        Producto productoExistente = new Producto();
        productoExistente.cambiarStock(5); // Stock inicial: 5

        // Le enseñamos al puerto qué responder cuando busquen la ID 1
        when(productoRepositoryPort.buscarPorId(id)).thenReturn(Optional.of(productoExistente));

        // Le enseñamos al puerto a retornar el mismo objeto que recibe al guardar
        when(productoRepositoryPort.guardar(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act (Ejecutar la lógica de la capa de aplicación)
        Producto resultado = gestionarProductoService.actualizarStock(id, 20);

        // Assert (Verificar el comportamiento de la capa de aplicación y del modelo)
        assertNotNull(resultado);
        assertEquals(20, resultado.getStockDisponible());

        // Verificamos que el servicio realmente interactuó con el puerto
        verify(productoRepositoryPort, times(1)).buscarPorId(id);
        verify(productoRepositoryPort, times(1)).guardar(productoExistente);
    }

    @Test
    @DisplayName("Debe lanzar ProductoNotFoundException cuando el ID no existe en el puerto")
    void lanzarExcepcionCuandoProductoNoExiste() {
        // Arrange
        Long idInexistente = 99L;
        when(productoRepositoryPort.buscarPorId(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProductoNotFoundException.class, () -> {
            gestionarProductoService.actualizarStock(idInexistente, 10);
        });

        // Verificamos que al fallar la búsqueda, NUNCA se llamó al método guardar
        verify(productoRepositoryPort, never()).guardar(any());
    }

    //Caso Éxito: Si el repositorio retorna un Optional.of(producto), el servicio debe devolver ese mismo producto sin errores.
    @Test
    @DisplayName("retornar productos en caso de que encuentre datos")
    void retornarProductos(){
        Descuento descuento = new Descuento(3L, "Descuento por porcentaje", TipoDescuento.PORCENTAJE, 10.0, LocalDateTime.of(2025, 5, 4, 0, 0), LocalDateTime.now(), true);
        Producto p= new Producto(1L, "hgk-62534", "coca-cola", "producto gaseosa", 25.00, 150, true, 2L, List.of(descuento));
        when(productoRepositoryPort.buscarPorId(1L)).thenReturn(Optional.of(p));

        Producto nuevoProducto= gestionarProductoService.obtenerPorId(1L);
        assertEquals(p, nuevoProducto, "el producto no es el mismo que el obtenido");

    }
    //Caso Excepción: Si el repositorio retorna Optional.empty(), el servicio debe lanzar ProductoNotFoundException. Debes validar la excepción con assertThrows y verificar que el repositorio sí fue consultado.
    @Test
    @DisplayName("retornar una excepcion en caso de que que el optional de producto venga vacio")
    void retornarVacio(){
        Descuento descuento = new Descuento(3L, "Descuento por porcentaje", TipoDescuento.PORCENTAJE, 10.0, LocalDateTime.of(2025, 5, 4, 0, 0), LocalDateTime.now(), true);
        Producto p= new Producto(1L, "hgk-62534", "coca-cola", "producto gaseosa", 25.00, 150, true, 2L, List.of(descuento));
        when(productoRepositoryPort.buscarPorId(99L)).thenReturn(Optional.empty());

        assertThrows(ProductoNotFoundException.class, () -> {
            gestionarProductoService.obtenerPorId(99L);
        });

    }
    //Caso Éxito: Si el repositorio retorna una lista con al menos un producto,
    // el servicio debe devolver esa lista y su tamaño debe coincidir.
    @Test
    @DisplayName("Retornar la misma cantidad de productos")
    void retornarMismaCantidadProductosEncontrados(){
        Descuento descuento = new Descuento(3L, "Descuento por porcentaje", TipoDescuento.PORCENTAJE, 10.0, LocalDateTime.of(2025, 5, 4, 0, 0), LocalDateTime.now(), true);
        Producto p= new Producto(1L, "hgk-62534", "coca-cola", "producto gaseosa", 25.00, 150, true, 2L, List.of(descuento));
        when(productoRepositoryPort.buscarTodos()).thenReturn(List.of(p));

        List<Producto> productos = gestionarProductoService.obtenerTodos();
        assertFalse(productos.isEmpty());
        assertEquals(1, productos.size());

    }
    //Caso Excepción: Si el repositorio retorna una lista vacía (List.of()),
    // tu código fuente dice que debe lanzar CategoriaNotFoundException.
    // Valida que se lance la excepción cuando la lista devuelta por el mock esté vacía.
    @Test
    @DisplayName("Validar cuando se genera una lista vacia")
    void LanzarExcepcionEnCasoListaVacia(){
        Descuento descuento = new Descuento(3L, "Descuento por porcentaje", TipoDescuento.PORCENTAJE, 10.0, LocalDateTime.of(2025, 5, 4, 0, 0), LocalDateTime.now(), true);
        Producto p= new Producto(1L, "hgk-62534", "coca-cola", "producto gaseosa", 25.00, 150, true, 2L, List.of(descuento));
        String nombreCategoria="Tecnologia";
        when(productoRepositoryPort.buscarPorCategoria(nombreCategoria))
                .thenReturn(List.of());

        assertThrows(CategoriaNotFoundException.class, ()->{
            gestionarProductoService.obtenerPorCategoria(nombreCategoria);
        });
    }

    //caso de exito: crear producto
    @Test
    @DisplayName("Debe crear un producto exitosamente cuando los datos son válidos")
    void crearProductoExitosamente() {
        Producto productoNuevo = new Producto(null, "SKU-100", "Galletas", "Snack", 10.0, 50, true, 1L, null);
        Producto productoGuardado = new Producto(1L, "SKU-100", "Galletas", "Snack", 10.0, 50, true, 1L, null);

        when(productoRepositoryPort.guardar(any(Producto.class))).thenReturn(productoGuardado);

        Producto resultado = gestionarProductoService.crearProducto(productoNuevo);

        assertNotNull(resultado);
        assertNotNull(resultado.getId());
        assertEquals("SKU-100", resultado.getSku());
        verify(productoRepositoryPort, times(1)).guardar(any(Producto.class));
    }
    //caso de excepcion: error creando producto

    //caso exitoso: obteniendo productos de la categoria
    @Test
    @DisplayName("Debe retornar lista de productos por categoría cuando existen coincidencias")
    void obtenerPorCategoriaExitoso() {
        String categoria = "Bebidas";
        Producto producto = new Producto(1L, "SKU-200", "Jugo", "Bebida natural", 15.0, 30, true, 1L, null);
        when(productoRepositoryPort.buscarPorCategoria(categoria)).thenReturn(List.of(producto));

        List<Producto> resultado = gestionarProductoService.obtenerPorCategoria(categoria);

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("Jugo", resultado.get(0).getNombre());
        verify(productoRepositoryPort, times(1)).buscarPorCategoria(categoria);
    }
    //caso de excepcion: que se presente error en caso de que la categoria no existe
}
