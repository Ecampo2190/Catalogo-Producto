package com.ecampo.catalogo_producto.domain.strategy;

public class EstrategiaDescuentoFactory {

    public static EstrategiaDescuento obtenerEstrategia(String tipoDescuento){
        if (tipoDescuento == null) {
            return new SinDescuentoStrategy();
        }
        return switch (tipoDescuento.toUpperCase()){
                case "PORCENTAJE" -> new DescuentoPorcentajeStrategy();
                case "MONTO_FIJO" -> new DescuentoMontoFijoStrategy();
                default -> new SinDescuentoStrategy();
        };
    }
}
