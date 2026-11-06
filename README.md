Microservicio encargado de gestionar el catálogo de productos, existencias en inventario y la aplicación dinámica de estrategias de descuento. 
Diseñado bajo los principios de Arquitectura Hexagonal, enfatizando la separación del dominio, persistencia relacional con MySQL, 
desacoplamiento mediante DTOs, aceleración de lecturas con Redis y documentación interactiva con OpenAPI/Swagger.

Tecnologías y Herramientas
Lenguaje: Java 21

Framework: Spring Boot 3.2.5

Persistencia: Spring Data JPA / Hibernate

Base de Datos: MySQL (Desarrollo/Producción), H2 (Pruebas de Integración)

Caché: Spring Data Redis (Cache-Aside Pattern)

Validación & Excepciones: Bean Validation (jakarta.validation), @ControllerAdvice (GlobalExceptionHandler)

Pruebas: JUnit 5, Mockito, MockMvc, TestEntityManager

Documentación: Springdoc OpenAPI UI (Swagger)

Gestor de Dependencias: Maven
