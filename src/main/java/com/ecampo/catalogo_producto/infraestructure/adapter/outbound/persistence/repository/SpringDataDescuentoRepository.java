package com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.repository;

import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity.DescuentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataDescuentoRepository extends JpaRepository<DescuentoEntity, Long> {
}
