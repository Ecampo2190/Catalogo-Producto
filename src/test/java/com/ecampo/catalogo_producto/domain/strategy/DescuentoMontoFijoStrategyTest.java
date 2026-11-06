package com.ecampo.catalogo_producto.domain.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescuentoMontoFijoStrategyTest {
    private DescuentoMontoFijoStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new DescuentoMontoFijoStrategy();
    }


    @Test
    @DisplayName("Debe restar el monto fijo correctamente")
    void aplicarDescuentoMontoFijoExitoso() {
        double precioBase = 1000.0;
        double descuento = 200.0;

        double resultado = strategy.aplicarDescuento(precioBase, descuento);

        assertEquals(800.0, resultado);
    }

    @Test
    @DisplayName("Debe retornar 0 si el monto de descuento supera el precio base")
    void retornarCeroCuandoDescuentoSuperaPrecioBase() {
        double precioBase = 100.0;
        double descuento = 150.0;

        double resultado = strategy.aplicarDescuento(precioBase, descuento);

        assertEquals(0.0, resultado, "El precio final nunca debe ser negativo");
    }
}
