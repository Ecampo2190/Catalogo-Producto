package com.ecampo.catalogo_producto.infrastructure.adapters.inbound.rest;

import com.ecampo.catalogo_producto.domain.exception.ProductoNotFoundException;
import com.ecampo.catalogo_producto.domain.model.Producto;
import com.ecampo.catalogo_producto.domain.ports.inbound.CalcularPrecioProductoUseCase;
import com.ecampo.catalogo_producto.domain.ports.inbound.ConsultarCatalogoUseCase;
import com.ecampo.catalogo_producto.domain.ports.inbound.ConsultarReportesCatalogoUseCase;
import com.ecampo.catalogo_producto.domain.ports.inbound.GestionarProductoUseCase;
import com.ecampo.catalogo_producto.infraestructure.adapter.inbound.rest.controller.ProductoController;
import com.ecampo.catalogo_producto.infraestructure.adapter.inbound.rest.dto.ProductoRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = ProductoController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ProductoControllerTest {
    @Autowired
    private MockMvc mockMvc; // Simula las peticiones HTTP sin levantar un servidor real

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ConsultarCatalogoUseCase consultarCatalogoUseCase; // Mock del caso de uso de entrada

    @MockBean
    private GestionarProductoUseCase gestionarProductoUseCase; // Mock del caso de uso de entrada

    @MockBean
    private ConsultarReportesCatalogoUseCase consultarReportesCatalogoUseCase;

    @MockBean
    private CalcularPrecioProductoUseCase calcularPrecioProductoUseCase;

    @Test
    @DisplayName("GET /productos/{id} debe retornar 200 OK y el producto cuando existe")
    void obtenerProductoPorIdExitoso() throws Exception {
        Long id = 1L;
        Producto productoDomain = new Producto(id, "SKU-123", "Gaseosa", "Bebida", 100.0, 10, true, 2L, null);

        when(consultarCatalogoUseCase.obtenerPorId(id)).thenReturn(productoDomain);

        mockMvc.perform(get("/productos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Gaseosa"));
    }

    @Test
    @DisplayName("GET /productos/{id} debe retornar 404 Not Found cuando el producto no existe")
    void obtenerProductoPorIdNoEncontrado() throws Exception {
        Long idInexistente = 99L;
        when(consultarCatalogoUseCase.obtenerPorId(idInexistente))
                .thenThrow(new ProductoNotFoundException("El producto con Id 99 no existe"));

        mockMvc.perform(get("/productos/{id}", idInexistente)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /productos debe crear el producto y retornar 201 Created cuando el DTO es válido")
    void crearProductoExitoso() throws Exception {
        ProductoRequestDto dto = new ProductoRequestDto("SKU-555", "Laptop", "Laptop Gamer", 1200.0, 10, 1L);
        Producto productoCreado = new Producto(1L, "SKU-555", "Laptop", "Laptop Gamer", 1200.0, 10, true, 1L, null);

        when(gestionarProductoUseCase.crearProducto(any(Producto.class))).thenReturn(productoCreado);

        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.sku").value("SKU-555"));
    }

    @Test
    @DisplayName("POST /productos debe retornar 400 Bad Request cuando las validaciones del DTO fallan")
    void crearProductoFallaValidacion() throws Exception {
        ProductoRequestDto dtoInvalido = new ProductoRequestDto("", "Laptop", "Laptop Gamer", -50.0, -5, null);

        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoInvalido)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigoError").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.descripcion").value("Error de Validación en los Datos de Entrada"));
    }

    @Test
    @DisplayName("GET /productos debe retornar 200 OK y la lista de productos")
    void consultarTodosExitoso() throws Exception {
        Producto p1 = new Producto(1L, "SKU-001", "Pan", "Panaderia", 2.0, 100, true, 1L, null);
        when(consultarCatalogoUseCase.obtenerTodos()).thenReturn(List.of(p1));

        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Pan"));
    }
}
