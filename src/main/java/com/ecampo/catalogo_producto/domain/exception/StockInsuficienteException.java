package com.ecampo.catalogo_producto.domain.exception;

public class StockInsuficienteException extends RuntimeException{

    public StockInsuficienteException(String message) {
        super(message);
    }
}
