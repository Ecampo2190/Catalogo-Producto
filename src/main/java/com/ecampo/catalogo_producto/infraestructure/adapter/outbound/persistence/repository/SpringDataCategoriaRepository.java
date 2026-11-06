package com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.repository;

import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataCategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    Optional<CategoriaEntity> findByNombre(String nombre);
}
