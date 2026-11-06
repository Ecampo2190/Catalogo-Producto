package com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence;

import com.ecampo.catalogo_producto.domain.model.Producto;
import com.ecampo.catalogo_producto.domain.ports.outbound.ProductoRepositoryPort;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity.ProductoEntity;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.mapper.ProductoPersistenceMapper;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.repository.SpringDataProductoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductoPersistenceAdapter implements ProductoRepositoryPort {

    private final SpringDataProductoRepository springDataProductoRepository;

    public ProductoPersistenceAdapter(SpringDataProductoRepository springDataProductoRepository) {
        this.springDataProductoRepository = springDataProductoRepository;
    }

    @Override
    @CacheEvict(value = "productos", key = "#producto.id", condition = "#producto.id != null")
    public Producto guardar(Producto producto) {
        ProductoEntity productoEntity = ProductoPersistenceMapper.toEntity(producto);
        ProductoEntity entityGuardada=springDataProductoRepository.save(productoEntity);
        return ProductoPersistenceMapper.toDomain(entityGuardada);
    }

    @Override
    @Cacheable(value = "productos", key = "#id", unless = "#result == null")
    public Optional<Producto> buscarPorId(Long id) {
        return springDataProductoRepository.findById(id)
                .map(ProductoPersistenceMapper::toDomain);
    }

    @Override
    public List<Producto> buscarTodos() {
       List<Producto>productos= springDataProductoRepository.findAll()
               .stream()
               .map(ProductoPersistenceMapper::toDomain)
               .toList();
        return productos;
    }

    @Override
    public List<Producto> buscarPorCategoria(String nombreCategoria) {
        return springDataProductoRepository.findByCategoriaNombre(nombreCategoria)
                .stream()
                .map(ProductoPersistenceMapper::toDomain)
                .toList();
    }
}
