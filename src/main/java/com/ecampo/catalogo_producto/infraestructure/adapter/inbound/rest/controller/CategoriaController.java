package com.ecampo.catalogo_producto.infraestructure.adapter.inbound.rest.controller;

import com.ecampo.catalogo_producto.application.dto.CategoriaDTO;
import com.ecampo.catalogo_producto.application.mapper.CategoriaMapper;
import com.ecampo.catalogo_producto.domain.model.Categoria;
import com.ecampo.catalogo_producto.domain.ports.inbound.ObtenerCategoriaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final ObtenerCategoriaUseCase obtenerCategoriaUseCase;


    public CategoriaController(ObtenerCategoriaUseCase obtenerCategoriaUseCase) {
        this.obtenerCategoriaUseCase = obtenerCategoriaUseCase;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
        List<Categoria> categorias = obtenerCategoriaUseCase.listarCategoria();
        List<CategoriaDTO> lista= categorias.stream()
                .map(CategoriaMapper::toDto).toList();

        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaDTO> obtenerCategoria(@PathVariable Long idCategoria){
        Categoria categoria=obtenerCategoriaUseCase.obtenerCategoriaPorId(idCategoria);
        CategoriaDTO consulta=CategoriaMapper.toDto(categoria);
        return ResponseEntity.ok(consulta);
    }
}
