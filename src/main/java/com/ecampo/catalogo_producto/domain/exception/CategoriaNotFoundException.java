package com.ecampo.catalogo_producto.domain.exception;

public class CategoriaNotFoundException extends RuntimeException{
    public CategoriaNotFoundException(String message) {
        super(message);
    }
}
