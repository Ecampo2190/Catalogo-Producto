package com.ecampo.catalogo_producto.infraestructure.adapter.inbound.rest.controller;

import com.ecampo.catalogo_producto.application.dto.ProductoDTO;
import com.ecampo.catalogo_producto.application.mapper.ProductoMapper;
import com.ecampo.catalogo_producto.domain.model.Producto;
import com.ecampo.catalogo_producto.domain.ports.inbound.CalcularPrecioProductoUseCase;
import com.ecampo.catalogo_producto.domain.ports.inbound.ConsultarCatalogoUseCase;
import com.ecampo.catalogo_producto.domain.ports.inbound.ConsultarReportesCatalogoUseCase;
import com.ecampo.catalogo_producto.domain.ports.inbound.GestionarProductoUseCase;
import com.ecampo.catalogo_producto.infraestructure.adapter.inbound.rest.dto.ProductoRequestDto;
import com.ecampo.catalogo_producto.infraestructure.adapter.inbound.rest.mapper.ProductoRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final GestionarProductoUseCase gestionarProductoUseCase;
    private final ConsultarCatalogoUseCase consultarCatalogoUseCase;
    private final ConsultarReportesCatalogoUseCase consultarReportesCatalogoUseCase;
    private final CalcularPrecioProductoUseCase calcularPrecioProductoUseCase;

    public ProductoController(GestionarProductoUseCase gestionarProductoUseCase, ConsultarCatalogoUseCase consultarCatalogoUseCase, ConsultarReportesCatalogoUseCase consultarReportesCatalogoUseCase, CalcularPrecioProductoUseCase calcularPrecioProductoUseCase) {
        this.gestionarProductoUseCase = gestionarProductoUseCase;
        this.consultarCatalogoUseCase = consultarCatalogoUseCase;
        this.consultarReportesCatalogoUseCase = consultarReportesCatalogoUseCase;
        this.calcularPrecioProductoUseCase = calcularPrecioProductoUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoRequestDto dto){

        Producto productoADominio = ProductoRestMapper.toDomain(dto);
        Producto productoGuardado = gestionarProductoUseCase.crearProducto(productoADominio);
        ProductoDTO respuestaDto = ProductoMapper.toDto(productoGuardado);

        return new ResponseEntity<>(respuestaDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestParam int cantidad){
        Producto productoActualizado =gestionarProductoUseCase.actualizarStock(id, cantidad);
        ProductoDTO respuestaDto = ProductoMapper.toDto(productoActualizado);
        return ResponseEntity.ok(respuestaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> consultarProductoXid(@PathVariable Long id){
        Producto productoConsultado = consultarCatalogoUseCase.obtenerPorId(id);
        ProductoDTO consulta=ProductoMapper.toDto(productoConsultado);
        return ResponseEntity.status(HttpStatus.OK).body(consulta);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> consultarTodos(){
        List<Producto> productos = consultarCatalogoUseCase.obtenerTodos();
        List<ProductoDTO> listaProductos=productos.stream().map(
                ProductoMapper::toDto
        ).toList();

        return ResponseEntity.ok().body(listaProductos);
    }

        @GetMapping("/listProductoPorCategoria")
        public ResponseEntity<List<ProductoDTO>> consultarPorNombreCategoria(@RequestParam String nombreCategoria){
        List<Producto> productos = consultarCatalogoUseCase.obtenerPorCategoria(nombreCategoria);
        List<ProductoDTO> listaProductos=productos.stream().map(
                ProductoMapper::toDto
        ).toList();
        return ResponseEntity.ok().body(listaProductos);
    }

    @GetMapping("/stock")
    public ResponseEntity<Integer> consultarStock(@RequestParam Long productoId){
        int cantidadStock=consultarReportesCatalogoUseCase.consultarStockDisponible(productoId);
        return ResponseEntity.ok(cantidadStock);
    }

    @GetMapping("/precioTotal/{nombreCategoria}")
    public ResponseEntity<Map<String, BigDecimal>> consultaPrecioTotalCategoria(@PathVariable String nombreCategoria){
        Map<String, BigDecimal> reporte=consultarReportesCatalogoUseCase.obtenerPrecioTotalPorCategoria(nombreCategoria);
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/{id}/precio-descuento")
    public ResponseEntity<Map<String, Object>> calcularPrecioConDescuento(
            @PathVariable Long id,
            @RequestParam(defaultValue = "SIN_DESCUENTO") String tipoDescuento,
            @RequestParam(defaultValue = "0.0") double valorDescuento) {

        double precioFinal = calcularPrecioProductoUseCase.calcularPrecioFinal(id, tipoDescuento, valorDescuento);

        Map<String, Object> respuesta = Map.of(
                "productoId", id,
                "tipoDescuento", tipoDescuento.toUpperCase(),
                "valorDescuento", valorDescuento,
                "precioFinal", precioFinal
        );

        return ResponseEntity.ok(respuesta);
    }

}
