    package com.ecampo.catalogo_producto.domain.model;

    import com.ecampo.catalogo_producto.domain.exception.StockInsuficienteException;
    import com.ecampo.catalogo_producto.domain.strategy.EstrategiaDescuento;

    import java.util.List;

    public class Producto {

        private Long id;
        private String sku;
        private String nombre;
        private String descripcion;
        private double precioBase;
        private int stockDisponible;
        private boolean activo;
        private Long categoriaId;
        private List<Descuento> descuentos;

        public Producto(){

        }

        public Producto(Long id, String sku, String nombre, String descripcion,
                        double precioBase, int stockDisponible, boolean activo,
                        Long categoriaId, List<Descuento> descuentos) {
            this.id = id;
            this.sku = sku;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precioBase = precioBase;
            this.stockDisponible = stockDisponible;
            this.activo = activo;
            this.categoriaId = categoriaId;
            this.descuentos = descuentos;
        }

    //platos, precios, categorias, descuentos, stock
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public double getPrecioBase() {
            return precioBase;
        }

        public void setPrecioBase(double precioBase) {
            this.precioBase = precioBase;
        }

        public int getStockDisponible() {
            return stockDisponible;
        }

        public void setStockDisponible(int stockDisponible) {
            this.stockDisponible = stockDisponible;
        }

        public boolean isActivo() {
            return activo;
        }

        public void setActivo(boolean activo) {
            this.activo = activo;
        }

        public Long getCategoriaId() {
            return categoriaId;
        }

        public void setCategoriaId(Long categoriaId) {
            this.categoriaId = categoriaId;
        }

        public List<Descuento> getDescuentos() {
            return descuentos;
        }

        public void setDescuentos(List<Descuento> descuentos) {
            this.descuentos = descuentos;
        }

        public void cambiarStock(int nuevoStock){
            if (nuevoStock < 0) {
                throw new StockInsuficienteException("El stock disponible no puede ser negativo: " + nuevoStock);
            }
            this.stockDisponible = nuevoStock;
        }

        public double calcularPrecioFinal(EstrategiaDescuento estrategia, double valorDescuento){
            if (estrategia == null) {
                return this.precioBase;
            }
            return estrategia.aplicarDescuento(this.precioBase, valorDescuento);
        }
    }
