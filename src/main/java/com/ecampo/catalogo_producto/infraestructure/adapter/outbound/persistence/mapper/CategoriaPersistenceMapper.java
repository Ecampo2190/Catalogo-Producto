package com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.mapper;

import com.ecampo.catalogo_producto.domain.model.Categoria;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity.CategoriaEntity;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity.ProductoEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoriaPersistenceMapper {

    public static CategoriaEntity toEntity(Categoria domain){
        if(domain==null) return null;
        CategoriaEntity entity = new CategoriaEntity();

        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setDescripcion(domain.getDescripcion());
        entity.setActiva(domain.isActiva());

        return entity;
    }

    public static Categoria toDomain(CategoriaEntity entity){
        if(entity==null) return null;
        return new Categoria(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.isActiva()
        );
    }
}
