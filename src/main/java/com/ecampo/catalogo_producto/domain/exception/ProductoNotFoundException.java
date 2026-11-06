package com.ecampo.catalogo_producto.domain.exception;

public class ProductoNotFoundException extends RuntimeException{
    public ProductoNotFoundException(String message) {
        super(message);
    }
}
