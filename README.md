# 📚 Literalura - Biblioteca Digital

[![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
[![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

**Literalura** es una aplicación de línea de comandos que permite explorar y gestionar una biblioteca digital utilizando la API pública de [Gutendex](https://gutendex.com/). Los usuarios pueden buscar libros, registrar sus favoritos y realizar diversas consultas sobre autores y obras literarias.

---

## ✨ Características principales

- 🔍 Buscar libros por título utilizando la API de Gutendex.
- 📥 Registrar libros en una base de datos local.
- 👤 Gestionar información de autores (nombre, años de vida).
- 📚 Listar libros registrados con detalles completos.
- 🎂 Encontrar autores vivos en un año específico.
- 🌍 Filtrar libros por idioma (Español, Inglés, Francés, Portugués, etc).

---

## ⚙️ Requisitos del sistema

- Java 21 o superior  
- MySQL 8.0 o superior  
- Maven 3.9+ (opcional si usas el wrapper)  
- Conexión a Internet (para consultar la API de Gutendex)  

---

## 🛠️ Configuración inicial

### 1. Crear la base de datos MySQL

```sql
CREATE DATABASE literalura;
```

### 2. Configurar las credenciales de conexión

Crea el archivo `src/main/resources/application.properties` con el siguiente contenido:

```properties
# Configuración de MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/literalura?useSSL=false&serverTimezone=UTC
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

# Configuración de JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Mostrar consultas SQL (opcional)
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

### 3. Ejecutar la aplicación

Con Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Con Maven instalado:

```bash
mvn spring-boot:run
```

O generar el `.jar` y ejecutarlo:

```bash
mvn clean package
java -jar target/literalura-0.0.1-SNAPSHOT.jar
```

---

## 💻 Uso de la aplicación

Al iniciar, se mostrará el siguiente menú:

```text
📚 Bienvenido a Literalura
1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un año
5 - Listar libros por idioma
0 - Salir
Seleccione una opción:
```

### Funcionalidades:

#### 1 - Buscar libro por título

- Ingresa el título (completo o parcial).
- Si hay múltiples coincidencias, se mostrará una lista para elegir.
- El libro seleccionado se registra en la base de datos local.

#### 2 - Listar libros registrados

- Muestra todos los libros guardados.
- Incluye título, idioma, autor y descargas.

#### 3 - Listar autores registrados

- Lista todos los autores registrados.
- Muestra nombre, año de nacimiento y defunción.

#### 4 - Listar autores vivos en un año

- Ingresa un año (ej. `1850`).
- Se muestran los autores que vivían ese año.

#### 5 - Listar libros por idioma

- Ingresa el código del idioma (ej. `en`, `es`, `fr`, `pt`).
- Se listan los libros registrados en ese idioma.

---

## 🗂️ Estructura del proyecto

```text
literalura/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── literalura/
│   │   │           ├── entity/         # Entidades JPA (Book, Author)
│   │   │           ├── repository/     # Repositorios de datos
│   │   │           ├── service/        # Lógica de negocio y API
│   │   │           ├── dto/            # Clases de transferencia de datos
│   │   │           └── LiteraluraApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                           # Pruebas unitarias
├── pom.xml                             # Configuración de Maven
└── README.md                           # Este archivo
```

---

## 🧩 Tecnologías utilizadas

- **Java 21**: Lenguaje principal del proyecto
- **Spring Boot 3.5.0**: Framework para desarrollo rápido
- **Spring Data JPA**: Acceso a datos con repositorios
- **MySQL**: Base de datos relacional
- **Hibernate**: ORM para manejo de entidades
- **RestTemplate**: Cliente HTTP para consumir Gutendex
- **Jackson**: Serialización y deserialización de JSON
- **Maven**: Gestión de dependencias y ciclo de vida del proyecto

---
