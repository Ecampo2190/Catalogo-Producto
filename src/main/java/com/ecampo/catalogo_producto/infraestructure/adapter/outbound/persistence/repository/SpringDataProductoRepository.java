package com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.repository;

import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataProductoRepository extends JpaRepository<ProductoEntity, Long> {
    List<ProductoEntity> findByCategoriaId(Long categoriaId);
    List<ProductoEntity> findByCategoriaNombre(String nombreCategoria);
}
