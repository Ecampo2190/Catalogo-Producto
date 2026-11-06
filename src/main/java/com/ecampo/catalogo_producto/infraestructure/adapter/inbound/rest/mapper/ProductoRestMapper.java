package com.ecampo.catalogo_producto.infraestructure.adapter.inbound.rest.mapper;

import com.ecampo.catalogo_producto.domain.model.Producto;
import com.ecampo.catalogo_producto.infraestructure.adapter.inbound.rest.dto.ProductoRequestDto;

import java.util.Collections;

public class ProductoRestMapper {
    public static Producto toDomain(ProductoRequestDto dto) {
        if (dto == null) return null;

        return new Producto(
                null,
                dto.getSku(),
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getPrecioBase(),
                dto.getStockDisponible(),
                true,
                dto.getCategoriaId(),
                Collections.emptyList()
        );
    }
}
