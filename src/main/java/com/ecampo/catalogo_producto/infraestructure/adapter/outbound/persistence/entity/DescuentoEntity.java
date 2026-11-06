package com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity;

import com.ecampo.catalogo_producto.domain.model.TipoDescuento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "descuento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DescuentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="tipo")
    @Enumerated(EnumType.STRING)
    private TipoDescuento tipo;
    @Column(name="valor")
    private double valor;
    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;
    @Column(name="fecha_fin")
    private LocalDateTime fechaFin;
    @Column(name="activo")
    private boolean activo;
    @ManyToMany(mappedBy = "descuentos")
    private Set<ProductoEntity> productos;
}
