package com.ecampo.catalogo_producto.infraestructure.adapter.inbound.rest.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime horaError;
    private String codigoError;
    private String descripcion;
    private String detalleMensaje;

    public ErrorResponse(LocalDateTime horaError, String codigoError, String descripcion, String detalleMensaje) {
        this.horaError = horaError;
        this.codigoError = codigoError;
        this.descripcion = descripcion;
        this.detalleMensaje = detalleMensaje;
    }

    public LocalDateTime getHoraError() {
        return horaError;
    }

    public void setHoraError(LocalDateTime horaError) {
        this.horaError = horaError;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalleMensaje() {
        return detalleMensaje;
    }

    public void setDetalleMensaje(String detalleMensaje) {
        this.detalleMensaje = detalleMensaje;
    }
}
