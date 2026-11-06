package com.ecampo.catalogo_producto.application.mapper;

import com.ecampo.catalogo_producto.application.dto.ProductoDTO;
import com.ecampo.catalogo_producto.domain.model.Producto;

public class ProductoMapper {

    public static Producto toDomain(ProductoDTO dto){
        if (dto == null) return null;
        return new Producto(
                dto.getId(),
                dto.getSku(), // El SKU o datos que no vienen en el DTO se completan si es necesario
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getPrecioBase(),
                dto.getStockDisponible(),
                dto.isActivo(),
                dto.getCategoriaId(),
                null // Descuentos
        );
    }

    public static ProductoDTO toDto(Producto producto) {
        if (producto == null) return null;

        return new ProductoDTO(
                producto.getId(),
                producto.getSku(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecioBase(),
                producto.getStockDisponible(),
                producto.isActivo(),
                producto.getCategoriaId()
        );
    }
}
