package com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence;

import com.ecampo.catalogo_producto.domain.model.Categoria;
import com.ecampo.catalogo_producto.domain.ports.outbound.CategoriaRepositoryPort;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity.CategoriaEntity;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.mapper.CategoriaPersistenceMapper;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.repository.SpringDataCategoriaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class CategoriaPersistenceAdapter implements CategoriaRepositoryPort {

    private final SpringDataCategoriaRepository springDataCategoriaRepository;

    public CategoriaPersistenceAdapter(SpringDataCategoriaRepository springDataCategoriaRepository) {
        this.springDataCategoriaRepository = springDataCategoriaRepository;
    }


    @Override
    public Categoria guardar(Categoria categoria) {
        CategoriaEntity categoriaEntity=CategoriaPersistenceMapper.toEntity(categoria);
        CategoriaEntity nuevaCategoria=springDataCategoriaRepository.save(categoriaEntity);

        return CategoriaPersistenceMapper.toDomain(nuevaCategoria);
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return springDataCategoriaRepository.findById(id)
                .map(CategoriaPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Categoria> buscarPorNombre(String nombre) {
        return springDataCategoriaRepository.findByNombre(nombre)
                .map(CategoriaPersistenceMapper::toDomain);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return springDataCategoriaRepository.findAll()
                .stream()
                .map(CategoriaPersistenceMapper::toDomain)
                .toList();
    }
}
