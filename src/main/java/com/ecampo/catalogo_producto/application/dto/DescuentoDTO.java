package com.ecampo.catalogo_producto.application.dto;

import com.ecampo.catalogo_producto.domain.model.TipoDescuento;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DescuentoDTO {

    private Long id;
    private String nombre;
    private TipoDescuento tipo;
    private double valor;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public DescuentoDTO(Long id, String nombre, TipoDescuento tipo, double valor,
                        LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoDescuento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDescuento tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
}
