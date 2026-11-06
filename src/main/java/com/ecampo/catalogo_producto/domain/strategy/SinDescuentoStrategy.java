package com.ecampo.catalogo_producto.domain.strategy;

public class SinDescuentoStrategy implements EstrategiaDescuento{
    @Override
    public double aplicarDescuento(double precioBase, double valorDescuento) {
        return precioBase;
    }
}
