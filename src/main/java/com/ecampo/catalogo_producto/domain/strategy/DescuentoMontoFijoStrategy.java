package com.ecampo.catalogo_producto.domain.strategy;

public class DescuentoMontoFijoStrategy implements EstrategiaDescuento{

    @Override
    public double aplicarDescuento(double precioBase, double valorDescuento) {
        double descuentoAplicado = precioBase-valorDescuento;
        if(descuentoAplicado<0) descuentoAplicado=0;
        return descuentoAplicado;
    }
}
