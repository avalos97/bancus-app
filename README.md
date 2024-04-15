# BancusApp

Aplicación web que simula el funcionamiento de un banco

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalado lo siguiente:
- Docker [Instalar Docker](https://docs.docker.com/get-docker/)
- Docker Compose (viene incluido en las instalaciones de Docker para Windows y Mac) [Docker Compose](https://docs.docker.com/compose/install/)

## Estructura del Proyecto

Descripción de la estructura de directorios de alto nivel:

/bancus-app

|-- /microservice-bank-api

|-- /bank-frontend

|-- docker-compose.yml

|-- init.sql

|-- README.md

- **microservice-bank-api**: Contiene todo el código fuente para el backend.
- **bank-frontend**: Contiene todo el código fuente para el frontend.
- **docker-compose.yml**: Archivo de Docker Compose para orquestar los servicios.
- **init.sql:** Script SQL para inicializar la base de datos.

## Configuración y Uso
Para poner en marcha el proyecto completo, sigue estos pasos:

### Clonar el Repositorio

`git clone https://github.com/avalos97/bancus-app.git`

`cd bancus-app`

### Iniciar los Servicios con Docker Compose
Una vez que tienes el proyecto en tu máquina local, puedes iniciar todos los servicios utilizando Docker Compose:

`docker-compose up --build`

### Acceder a los Servicios
Una vez que los servicios estén en ejecución, podrás acceder a ellos a través de los siguientes enlaces:

- Frontend: http://localhost:4200
- Backend (API): http://localhost:8080/microservice-bank-api/api/v1/

### Detener los Servicios
Para detener los servicios y remover los contenedores, puedes usar el siguiente comando:

`docker-compose down`
