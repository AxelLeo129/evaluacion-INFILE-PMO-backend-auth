CREATE TABLE users (
    id INT PRIMARY KEY NOT NULL,            -- Identificador único de usuario
    name VARCHAR(255) NOT NULL,             -- Nombre del usuario
    email VARCHAR(255) NOT NULL UNIQUE,     -- Correo electrónico único
    password VARCHAR(255) NOT NULL          -- Contraseña (debe almacenarse encriptada)
);


CREATE SEQUENCE users_id_seq INCREMENT 1 START 1  --- Crear secuencia para autoincrementar el id;