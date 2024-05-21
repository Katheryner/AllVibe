# ALL VIBE

## Descripción

Este proyecto es una aplicación de backend para gestionar una base de datos de eventos utilizando Spring Boot, JPA y MySQL. Además, se incluye Swagger para la documentación de la API.

## Requisitos

- Java 17 o superior
- Maven 3.6.3 o superior
- MySQL 8.0 o superior

## Instalación

### Paso 1: Clonar el repositorio

`
git clone https://github.com/Katheryner/AllVibe.git
cd AllVibe
`

### Paso 2: Crear database

`
CREATE DATABASE events_projects;
`
### Paso 3: Configurar src/main/resources/application.properties.

`
spring.datasource.username=tu-usuario
spring.datasource.password=tu-contraseña
`

### Paso 4: Construir y ejercutar proyecto

`
mvn clean install
mvn spring-boot:run
`
### Paso 5: Documentacion

`
http://localhost:8080/api/v1/swagger-ui/index.html
`

ALL VIBE

Versión: Versión 1

Integrantes: Keiber Lázaro, Jeison Ortiz, Katheryne Ramírez


    1. Introducción: 

Nuestra plataforma está dedicada a proporcionar información y recursos sobre una amplia variedad de eventos. Ya sea que estés buscando conciertos, festivales, conferencias, ferias comerciales o eventos deportivos, nuestro sitio web está diseñado para ayudarte a descubrir y participar en experiencias emocionantes y significativas. Con una interfaz fácil de usar y funciones interactivas, estamos aquí para simplificar tu proceso de planificación y mantenerte al tanto de los eventos más relevantes en tu área.

        1.1. Propósito: 
El propósito principal de nuestro sitio web es servir como un recurso integral para aquellos que buscan información sobre eventos. Nos esforzamos por proporcionar una plataforma centralizada donde los usuarios puedan explorar una amplia gama de eventos, desde pequeñas reuniones locales hasta grandes espectáculos. Nuestro objetivo es facilitar la conexión entre organizadores de eventos y asistentes, promoviendo la participación en experiencias enriquecedoras y fomentando la comunidad a través de la celebración de eventos culturales, educativos, deportivos y de entretenimiento.

        1.2. Alcance: 
	Nuestra página web ofrece una amplia variedad de funciones y servicios para satisfacer las necesidades de diversos usuarios. Esto incluye:

	- Búsqueda avanzada de eventos por ubicación, fecha, categoría y palabras clave.
	- Perfiles detallados de eventos que incluyen información sobre el lugar, horarios, precios de entrada y enlaces de compra de entradas.
	- Funcionalidad de registro y RSVP para eventos que requieren confirmación de asistencia.
	- Integración con calendarios y notificaciones para mantener a los usuarios informados sobre eventos próximos.
	- Características interactivas como reseñas de usuarios, calificaciones y comentarios para compartir experiencias y recomendaciones.
	- Herramientas para que organizadores de eventos gestionen y promocionen sus eventos de manera efectiva.
	- El usuario que cree el evento tendrá la posibilidad de editarlo o eliminarlo.

	- Habrá un historial de eventos global.
	

        1.3. Definiciones, acrónimos y abreviaturas: 

	- Evento
	- Crud (create, read, update, delete)
	- Base de datos relacional.
	- Aplicación Web: software que se ejecuta en un navegador web.

    2. Descripción general:
2.1 Perspectiva del producto

La página web ofrece una experiencia fácil y conveniente para usuarios de todas las edades e intereses, gracias a una interfaz intuitiva y funciones interactivas. Esto permite conectar con eventos sin necesidad de conocer al organizador o a algún participante, proporcionando una amplia variedad de opciones.
2.2 Funciones del producto:

El software permitirá crear un usuario, a su vez, tendrá dos botones principales “create new event” que permitirá la creación de eventos con determinadas especificaciones como serían el día, hora, lugar, tipo… y “view events” que permitirá la visualización de los eventos ya creados para que los usuarios se puedan inscribir en los que deseen participar.

El creador del evento podrá editarlo y/o eliminarlo, los usuarios que participen en eventos podrán dar una reseña del evento.

Pasada la hora del evento se archivará.

        2.3 Usuarios y características

Usuarios Potenciales:

Creadores de Eventos:

    • Personas que desean organizar eventos.
    • Tienen la capacidad de crear, editar y eliminar eventos.
    • Pueden especificar detalles como la fecha, hora, ubicación, costo y cantidad de usuarios requeridos(de ser necesario).

Participantes en Eventos:

    • Usuarios interesados.
    • Pueden buscar eventos disponibles y unirse a ellos indicando su disponibilidad.
    • Tienen la capacidad de modificar su disponibilidad para un evento específico.



Administradores del Sitio:

    • Responsables de gestionar el sistema en su totalidad.
    • Pueden moderar eventos, usuarios y resolver problemas técnicos.
    • Tienen acceso privilegiado a la funcionalidad de administración.


    3. Requerimientos específicos

        3.1. Requerimientos funcionales:

El sistema debe permitir a los usuarios: 
    • Registrarse.
    • Iniciar sesión.
    • Crear eventos.
    • Visualizar eventos
    • Editar eventos
    • Eliminar eventos
    • Reservar eventos

        3.2. Requerimientos no funcionales:

    • El sistema debe responder a las solicitudes del usuario en menos de 2 segundos. 
    • El sistema debe cifrar las contraseñas de los usuarios.
    • El sistema debe almacenar datos de usuarios.

## Diagrama

<p align="center">
    <img src="/image.png">
</p>

## uml
![Diagrama](https://github.com/Katheryner/AllVibe/blob/main/Diagrama%20de%20clases.svg)
