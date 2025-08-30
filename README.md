# API REST - Tienda de Vehículos

API REST desarrollada con **Node.js (Express)** y **MySQL2**, que permite realizar operaciones CRUD sobre la tabla `vehiculos`.  
Este repositorio también contiene la **app Android (AppTiendaVehiculo/)** que consume la API.


```
Un pequeño diagrama de estructura del repositorio que puede ayudar:

TIENDA_VEH/
├── js/                  # Archivos js
├── db/                  # Scripts SQL de la base de datos
├── AppTiendaVehiculo/   # Proyecto Android Studio
├── .env                 # Variables de entorno (ignorado en Git)
├── package.json
└── README.md
```

---

---

## Requisitos

- [Node.js](https://nodejs.org/)
- [MySQL](https://dev.mysql.com/downloads/)
- npm 

---

## Base de Datos

Ejecuta el siguiente script en tu servidor MySQL para crear la base de datos y la tabla `vehiculos`:

```sql
CREATE DATABASE IF NOT EXISTS tiendaveh;
USE tiendaveh;

CREATE TABLE IF NOT EXISTS vehiculos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(30) NOT NULL,
    modelo VARCHAR(30) NOT NULL,
    color VARCHAR(30) NOT NULL,
    precio DECIMAL(9,2) NOT NULL,
    placa CHAR(7) NOT NULL,
    CONSTRAINT uk_placa_veh UNIQUE (placa)
) ENGINE=InnoDB;

INSERT INTO vehiculos (marca, modelo, color, precio, placa) VALUES
('Nissan', 'Frontier','Gris',145000,'ABC-111'),
('Toyota', 'Hilux','Blanco',98000,'ABC-222');
```

## Instalación

- Clona el repositorio e instala las dependencias:

  [npm install express mysql2 dotenv nodemon]


Crea un archivo .env en la raíz del proyecto con la configuración de tu base de datos:

```
PORT=3000
DB_HOST=localhost
DB_USER=tu_usuario
DB_PASSWORD=tu_password
DB_NAME=tiendaveh
```
## Ejecución

Para iniciar el servidor con nodemon:

nodemon app


### El servidor estará disponible en:

(http://localhost:3000/)

# 📱 App Android (AppTiendaVehiculo)

Dentro de la carpeta `AppTiendaVehiculo/` se encuentra la app móvil desarrollada en **Android Studio**.

### Requisitos
- [Android Studio](https://developer.android.com/studio)
- SDK de Android configurado
- Emulador o dispositivo físico conectado

### Configuración
1. Abre **Android Studio**.
2. Selecciona **File > Open...** y abre la carpeta `AppTiendaVehiculo/`.
3. Revisa que el archivo `local.properties` tenga la ruta correcta de tu SDK (Android Studio lo genera automáticamente).
4. En caso de necesitar modificar la URL del backend, edita la clase que maneja las **peticiones HTTP** (normalmente en `ApiClient.java` o en una constante de tu proyecto) y coloca tu endpoint, por ejemplo:
   ```java
   public static final String BASE_URL = "http://192.168.1.10/"; 

---
* Proyecto Seguira En Avances 
