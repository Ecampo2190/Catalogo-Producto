package com.ecampo.catalogo_producto.domain.model;

import com.ecampo.catalogo_producto.domain.exception.StockInsuficienteException;
import com.ecampo.catalogo_producto.domain.strategy.DescuentoPorcentajeStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductoTest {

    private Producto producto;

    @BeforeEach
    void Setup(){
        producto = new Producto();
    }

    @Test
    @DisplayName("validar el cambio de stock con un stock valido")
    void validarStockValido(){
        producto.cambiarStock(10);
        assertEquals(10, producto.getStockDisponible(), "Error Stock no es igual a 10");

        //@DisplayName, assertEquals, assertThrows e assertInstanceOf
    }

    @Test
    @DisplayName("validar el cambio de stock con un stock negativo")
    void validarStockNegativo(){

        assertThrows(StockInsuficienteException.class,
                ()->{
            producto.cambiarStock(-5);
        });
    }

    @Test
    @DisplayName("Calculo del precio final")
    void calculoPrecioFinal(){
        producto.setPrecioBase(100.0);
        double descuento = 20.0;
        DescuentoPorcentajeStrategy estrategia= new DescuentoPorcentajeStrategy();
        //estrategia.aplicarDescuento(precioBase, descuento);
        double resultado=producto.calcularPrecioFinal(estrategia, descuento);
        System.out.println("Imprimir "+resultado);
        assertEquals(80.0, resultado, "El valor no corresponde con resultado final");
    }

    @Test
    @DisplayName("Calculo del precio final con estrategia nula")
    void calculoPrecioFinalConEstrategiaNula(){
        producto.setPrecioBase(100.0);
        double descuento = 20.0;
        DescuentoPorcentajeStrategy estrategia= new DescuentoPorcentajeStrategy();
        //estrategia.aplicarDescuento(precioBase, descuento);
        double resultado=producto.calcularPrecioFinal(null, descuento);
        assertEquals(100.0, resultado, "El valor no corresponde con resultado final");
    }

}
