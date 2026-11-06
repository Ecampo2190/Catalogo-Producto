package com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.mapper;

import com.ecampo.catalogo_producto.domain.model.Descuento;
import com.ecampo.catalogo_producto.domain.model.Producto;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity.CategoriaEntity;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity.DescuentoEntity;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity.ProductoEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductoPersistenceMapper {

    public static ProductoEntity toEntity(Producto domain){
        if(domain==null) return null;
        CategoriaEntity categoria = null;

        if (domain.getCategoriaId() != null) {
            categoria = new CategoriaEntity();
            categoria.setId(domain.getCategoriaId());
        }
        // 2. Mapeo de Descuentos (List<Descuento> -> Set<DescuentoEntity>)
        Set<DescuentoEntity> descuentos = Collections.emptySet();
        if (domain.getDescuentos() != null && !domain.getDescuentos().isEmpty()) {
            descuentos = domain.getDescuentos().stream()
                    .map(DescuentoPersistenceMapper::toEntity)
                    .collect(Collectors.toSet());
        }

        ProductoEntity entity = new ProductoEntity();
        entity.setId(domain.getId());
        entity.setSku(domain.getSku());
        entity.setNombre(domain.getNombre());
        entity.setDescripcion(domain.getDescripcion());
        entity.setPrecioBase(domain.getPrecioBase());
        entity.setStockDisponible(domain.getStockDisponible());
        entity.setActivo(domain.isActivo());
        entity.setCategoria(categoria);
        entity.setDescuentos(descuentos);

        return entity;
    }

    public static Producto toDomain(ProductoEntity entity){
        if(entity == null) return null;
        List<Descuento> descuentos= new ArrayList<>();
        if(entity.getDescuentos()!=null && !entity.getDescuentos().isEmpty()){
            descuentos= entity.getDescuentos().stream().
                    map(DescuentoPersistenceMapper::toDomain)
                    .collect(Collectors.toList());
        }

        return new Producto(
                entity.getId(),
                entity.getSku(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getPrecioBase(),
                entity.getStockDisponible(),
                entity.isActivo(),
                entity.getCategoria() != null ? entity.getCategoria().getId() : null,
                descuentos

        );


    }
}
