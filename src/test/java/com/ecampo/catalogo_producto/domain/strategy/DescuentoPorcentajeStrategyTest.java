package com.ecampo.catalogo_producto.domain.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DescuentoPorcentajeStrategyTest {
    private DescuentoPorcentajeStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new DescuentoPorcentajeStrategy();
    }

    @Test
    @DisplayName("Debe aplicar el porcentaje de descuento correctamente")
    void aplicarPorcentajeDescuento(){
        double precioBase = 100;
        double porcentaje = 15;
        double respuesta=strategy.aplicarDescuento(precioBase, porcentaje);
        assertEquals(85, respuesta, "El valor no corresponde");

    }

    @Test
    @DisplayName("Debe aplicar correctamente un descuento del 20%")
    void aplicarDescuentoPorcentajeExitoso() {
        // Arrange (Preparar datos)
        double precioBase = 100.0;
        double porcentaje = 20.0;

        // Act (Ejecutar el método a probar)
        double resultado = strategy.aplicarDescuento(precioBase, porcentaje);

        // Assert (Verificar que el resultado sea 80.0)
        assertEquals(80.0, resultado, "El precio con 20% de descuento sobre 100 debe ser 80");
    }

    @Test
    @DisplayName("Debe lanzar una excepción si el porcentaje es menor a 0 o mayor a 100")
    void lanzarExcepcionCuandoPorcentajeEsInvalido() {
        double precioBase = 100.0;

        // Verificar que un porcentaje de -10 lance excepción
        assertThrows(IllegalArgumentException.class, () -> {
            strategy.aplicarDescuento(precioBase, -10.0);
        });

        // Verificar que un porcentaje de 150 lance excepción
        assertThrows(IllegalArgumentException.class, () -> {
            strategy.aplicarDescuento(precioBase, 150.0);
        });
    }
}
