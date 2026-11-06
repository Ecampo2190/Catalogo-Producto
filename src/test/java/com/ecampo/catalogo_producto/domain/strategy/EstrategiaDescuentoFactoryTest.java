package com.ecampo.catalogo_producto.domain.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;


public class EstrategiaDescuentoFactoryTest {
    private EstrategiaDescuentoFactory strategy;

    @BeforeEach
    void setup(){
        strategy = new EstrategiaDescuentoFactory();
    }

    @Test
    @DisplayName("valida que al pasar porcentaje por parametro se seleccione la estrategia correcta")
    void validarEstrategiaDesceunto(){
        EstrategiaDescuento resultado=EstrategiaDescuentoFactory.obtenerEstrategia("PORCENTAJE");
        assertInstanceOf(DescuentoPorcentajeStrategy.class, resultado);
    }

    @Test
    @DisplayName("valida que al pasar monto fijo por parametro se seleccione la estrategia correcta")
    void validarEstrategiaMontoFijo(){
        EstrategiaDescuento resultado=EstrategiaDescuentoFactory.obtenerEstrategia("MONTO_FIJO");
        assertInstanceOf(DescuentoMontoFijoStrategy.class, resultado);
    }

    @Test
    @DisplayName("valida que al pasar null fijo por parametro se seleccione la estrategia correcta")
    void validarEstrategiaSinDescuento(){
        EstrategiaDescuento resultado=EstrategiaDescuentoFactory.obtenerEstrategia("CUALQUIER_TEXTO");
        assertInstanceOf(SinDescuentoStrategy.class, resultado);
    }

}
