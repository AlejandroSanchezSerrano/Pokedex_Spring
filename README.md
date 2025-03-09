# Pokedex Spring

## Descripción
Este es un proyecto de Spring Boot desarrollado por Alejandro Sanchez Serrano y Manuel Valero Cortes. La aplicación es una Pokédex que permite a los usuarios gestionar y visualizar información sobre diferentes Pokémon.

## Enlace a Proyecto en Render
https://pokedex-spring.onrender.com

## Tecnologías Utilizadas
- **Spring Boot**
- **Spring Web**
- **Spring Security**
- **Thymeleaf**
- **JPA**
- **MySQL** (para desarrollo local)
- **PostgreSQL** (para producción)
- **Springdoc OpenAPI**
- **Lombok**
- **Java 21**
- **HTML 5**
- **CSS 3**
- **SASS**
- **Bootstrap 5**
- **Docker**
- **Render**

## Configuración del Proyecto
### Prerrequisitos
- Java 21 o superior
- Maven
- Docker (opcional, para despliegue en contenedores)

### Sistema de Usuarios
La aplicación utiliza Spring Security para la autenticación y autorización de usuarios. La configuración de seguridad se encuentra en `SecurityConfiguration.java` y permite lo siguiente:

- **Rutas protegidas**: Las rutas `/pokemon/nuevo` y `/pokemon/agregar` requieren autenticación.
- **Inicio de sesión**: La página de inicio de sesión está disponible en `/login` y redirige a `/pokemon` después de un inicio de sesión exitoso.
- **Cierre de sesión**: La ruta de cierre de sesión está configurada en `/logout` y redirige a `/logout-success` después de un cierre de sesión exitoso.
- **Usuarios en memoria**: Actualmente, hay un usuario administrador definido en memoria para autenticarte y poder gestionar los datos de la página.

### Instalación
1. Clona el repositorio:
    ```bash
    git clone https://github.com/AlejandroSanchezSerrano/Pokedex_Spring.git
    ```

2. Navega al directorio del proyecto:
    ```bash
    cd Pokedex_Spring
    ```

3. Construye el proyecto con Maven:
    ```bash
    mvn clean install
    ```

### Configuración de la Base de Datos
#### Local (MySQL)
Para configurar la base de datos MySQL local, descomenta y ajusta las siguientes líneas en `src/main/resources/application.properties`:
```properties
# spring.datasource.url=jdbc:mysql://localhost:3306/pokedex?serverTimezone=UTC
# spring.datasource.username=root
# spring.datasource.password=
# spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
# spring.jpa.hibernate.ddl-auto=update
```

#### Producción (PostgreSQL)
La configuración de la base de datos para producción ya está incluida en el archivo `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://dpg-cv531p8gph6c73anho50-a.frankfurt-postgres.render.com/pokedex_h9i2
spring.datasource.username=pokedex_h9i2_user
spring.datasource.password=J5ExFsI3U2GqrQl3EQ07uafxIxyXNTt3
spring.jpa.hibernate.ddl-auto=update
```

### Ejecución
Para ejecutar la aplicación, utiliza el siguiente comando:
```bash
mvn spring-boot:run
```

### Documentación de la API
La documentación de la API está disponible a través de Springdoc OpenAPI. Para acceder a la documentación, asegúrate de que las siguientes propiedades estén activadas en `application.properties`:
```properties
# springdoc.api-docs.enabled=true
# springdoc.swagger-ui.enabled=true
```
Luego, accede a `http://localhost:8080/swagger-ui.html` en tu navegador.

## Despliegue con Docker
Para desplegar la aplicación en un contenedor Docker, utiliza el siguiente comando:
```bash
docker-compose down
docker-compose up -d
```

## Contribución
Si deseas contribuir a este proyecto, por favor sigue estos pasos:
1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz commit (`git commit -am 'Añadir nueva característica'`).
4. Haz push a la rama (`git push origin feature/nueva-caracteristica`).
5. Abre un Pull Request.

## Autores
- **Alejandro Sanchez Serrano**
- **Manuel Valero Cortes**
