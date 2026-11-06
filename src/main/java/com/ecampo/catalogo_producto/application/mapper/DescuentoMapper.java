package com.ecampo.catalogo_producto.application.mapper;

import com.ecampo.catalogo_producto.application.dto.DescuentoDTO;
import com.ecampo.catalogo_producto.domain.model.Descuento;

public class DescuentoMapper {

    public static Descuento toDomain(DescuentoDTO dto){
        if(dto==null) return null;

        return new Descuento(
               dto.getId(),
               dto.getNombre(),
               dto.getTipo(),
               dto.getValor(),
               dto.getFechaInicio(),
               dto.getFechaFin(),
               true
        );
    }

    public static DescuentoDTO toDto(Descuento descuento){
        return new DescuentoDTO(
                descuento.getId(),
                descuento.getNombre(),
                descuento.getTipo(),
                descuento.getValor(),
                descuento.getFechaInicio(),
                descuento.getFechaFin()
        );
    }
}
