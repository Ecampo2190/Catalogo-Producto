package com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = true, name = "sku")
    private String sku;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio_base")
    private double precioBase;
    @Column(name = "stock_disponible")
    private int stockDisponible;
    @Column(name = "activo")
    private boolean activo;
    @ManyToOne
    @JoinColumn(name="categoria_id")
    private CategoriaEntity categoria;
    @ManyToMany
    @JoinTable(
            name = "producto_descuento",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "descuento_id")
    )
    private Set<DescuentoEntity> descuentos;

}
