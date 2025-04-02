# CHALLENGE BACKEND

**CHALLENGE BACKEND** es una aplicación implementada usando el framework Spring Boot y utilizando una base de datos local de MySQL. La aplicación expone dos APIs RESTful que permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para gestionar clientes, cuentas bancarias y movimientos.

## Tecnologías

- **Spring Boot**: Framework para el desarrollo de aplicaciones Java basadas en microservicios.
- **MySQL**: Base de datos relacional para almacenar la información.
- **JPA (Java Persistence API)**: Para interactuar con la base de datos utilizando entidades.
- **Spring Data JPA**: Simplifica el acceso a datos.
- **JUnit**: Para la implementación de pruebas unitarias.
  
## Funcionalidades

- **Clientes**: CRUD para gestionar clientes.
- **Cuentas**: CRUD para gestionar cuentas bancarias, con operaciones de saldo y tipo de cuenta.
- **Movimientos**: CRUD para gestionar los movimientos de las cuentas, con validaciones de saldo suficiente y tipo de transacción.


## Requisitos

1. **Java 11 o superior**: Asegúrate de tener instalado Java en tu máquina.
2. **MySQL**: Configurado y corriendo en el puerto `3306`.
3. **Configuración de la base de datos**: Actualiza el archivo `application.properties` con las credenciales de tu base de datos.

## Instalación

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/CarlosMaldonado1998/challenge-back.git
   ```
2. Navega al directorio del microservicio que deseas ejecutar:
   ```bash
   cd microservice1
   ```
   o
   ```bash
   cd microservice2
   ```
3. Ejecuta el siguiente comando para compilar y ejecutar el microservicio:
   ```bash
   ./mvnw spring-boot:run
   ```


## Archivos adicionales

1. **Colección de Postman**: [postman/collection.postman_collection.json](postman/collection.postman_collection.json)  
   Contiene la colección de endpoints para probar la API.

2. **Script SQL**: [sql/BaseDatos.sql.sql](sql/BaseDatos.sql.sql)  
   Contiene los scripts necesarios para crear la base de datos y las tablas para la aplicación.

## Estructura del Proyecto

```
├── microservice1/
│   ├── src/
│   │   ├── main/
│   │   ├── test/
│   ├── pom.xml
│   └── mvnw
├── microservice2/
│   ├── src/
│   │   ├── main/
│   │   ├── test/
│   ├── pom.xml
│   └── mvnw
├── postman/
│   └── collection.postman_collection.json
├── sql/
│   └── BaseDatos.sql.sql
└── README.md
```


## Notas

- Asegúrate de que el puerto de MySQL no esté ocupado por otro servicio.
- Puedes personalizar las configuraciones de los microservicios en sus respectivos archivos `application.properties`.
