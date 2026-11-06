package com.ecampo.catalogo_producto.domain.strategy;

public interface EstrategiaDescuento {

    double aplicarDescuento(double precioBase, double valorDescuento);
}
