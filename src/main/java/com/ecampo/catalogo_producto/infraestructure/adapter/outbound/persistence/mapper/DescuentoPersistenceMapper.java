package com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.mapper;

import com.ecampo.catalogo_producto.domain.model.Descuento;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity.DescuentoEntity;

public class DescuentoPersistenceMapper {

    public static DescuentoEntity toEntity(Descuento domain){
        if(domain==null) return null;
        DescuentoEntity entity = new DescuentoEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setTipo(domain.getTipo());
        entity.setValor(domain.getValor());
        entity.setFechaInicio(domain.getFechaInicio());
        entity.setFechaFin(domain.getFechaFin());
        entity.setActivo(domain.isActivo());
        entity.setProductos(null);
        return entity;

    }

    public static Descuento toDomain(DescuentoEntity entity) {
        if (entity == null) return null;

        return new Descuento(
                entity.getId(),
                entity.getNombre(),
                entity.getTipo(),
                entity.getValor(),
                entity.getFechaInicio(),
                entity.getFechaFin(),
                entity.isActivo()
        );
    }
}
