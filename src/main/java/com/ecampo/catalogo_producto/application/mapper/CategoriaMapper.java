package com.ecampo.catalogo_producto.application.mapper;

import com.ecampo.catalogo_producto.application.dto.CategoriaDTO;
import com.ecampo.catalogo_producto.domain.model.Categoria;

public class CategoriaMapper {

    public static Categoria toDomain(CategoriaDTO dto){
        if (dto ==null) return null;

        return new Categoria(
                dto.getId(),
                dto.getNombre(),
                dto.getDescripcion(),
                true
        );
    }

    public static CategoriaDTO toDto(Categoria categoria){
        if(categoria==null) return null;
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }
}
