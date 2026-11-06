package com.ecampo.catalogo_producto.domain.strategy;

public class DescuentoPorcentajeStrategy implements EstrategiaDescuento{
    @Override
    public double aplicarDescuento(double precioBase, double valorDescuento) {
        if(valorDescuento <0 || valorDescuento > 100){
            throw new IllegalArgumentException("el porcentaje de descuento debe estar entre 0 y 100");
        }
        double descuentoAplicado=precioBase*(valorDescuento/100);
        return precioBase-descuentoAplicado;
    }
}
