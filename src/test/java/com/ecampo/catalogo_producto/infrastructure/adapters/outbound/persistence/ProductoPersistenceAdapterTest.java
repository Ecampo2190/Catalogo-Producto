package com.ecampo.catalogo_producto.infrastructure.adapters.outbound.persistence;

import com.ecampo.catalogo_producto.domain.model.Producto;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.ProductoPersistenceAdapter;
import com.ecampo.catalogo_producto.infraestructure.adapter.outbound.persistence.entity.CategoriaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect"
}) // Configura repositorios JPA e inicia H2 en memoria automáticamente
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import({ProductoPersistenceAdapter.class})
public class ProductoPersistenceAdapterTest {

    @Autowired
    private ProductoPersistenceAdapter productoPersistenceAdapter;

    @Autowired
    private TestEntityManager entityManager;

    private Long categoriaIdValido;

    @BeforeEach
    void setUp() {
        // Se inserta previamente la categoría padre para cumplir la restricción de Clave Foránea
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setNombre("Tecnologia");
        categoria.setDescripcion("Categoria para pruebas");
        categoria.setActiva(true);

        CategoriaEntity categoriaGuardada = entityManager.persistAndFlush(categoria);
        this.categoriaIdValido = categoriaGuardada.getId();
    }

    @Test
    @DisplayName("Debe guardar un producto de dominio en la base de datos y asignar un ID")
    void guardarProductoExitosamente() {
        Producto productoNuevo = new Producto(null, "SKU-999", "Teclado Mecanico", "RGB", 250.0, 15, true, categoriaIdValido, null);

        Producto productoGuardado = productoPersistenceAdapter.guardar(productoNuevo);

        assertNotNull(productoGuardado);
        assertNotNull(productoGuardado.getId());
        assertEquals("Teclado Mecanico", productoGuardado.getNombre());
    }

    @Test
    @DisplayName("Debe buscar y retornar un producto por ID cuando existe en la base de datos")
    void buscarPorIdExistente() {
        Producto productoInicial = new Producto(null, "SKU-888", "Mouse Inalambrico", "Ergonomico", 80.0, 5, true, categoriaIdValido, null);
        Producto guardado = productoPersistenceAdapter.guardar(productoInicial);

        Optional<Producto> resultado = productoPersistenceAdapter.buscarPorId(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Mouse Inalambrico", resultado.get().getNombre());
        assertEquals("SKU-888", resultado.get().getSku());
    }

    @Test
    @DisplayName("Debe retornar Optional.empty() cuando el producto no existe en la base de datos")
    void buscarPorIdInexistente() {
        Optional<Producto> resultado = productoPersistenceAdapter.buscarPorId(999L);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Debe retornar la lista completa de productos guardados")
    void buscarTodosLosProductos() {
        Producto p1 = new Producto(null, "SKU-001", "Agua", "Bebida", 5.0, 50, true, categoriaIdValido, null);
        Producto p2 = new Producto(null, "SKU-002", "Jugo", "Bebida", 8.0, 30, true, categoriaIdValido, null);

        productoPersistenceAdapter.guardar(p1);
        productoPersistenceAdapter.guardar(p2);

        var productos = productoPersistenceAdapter.buscarTodos();

        assertFalse(productos.isEmpty());
        assertTrue(productos.size() >= 2);
    }
}
