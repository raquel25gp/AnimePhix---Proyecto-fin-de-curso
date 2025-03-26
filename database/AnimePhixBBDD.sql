-- DROP DATABASE bbddAnimePhix;
CREATE DATABASE IF NOT EXISTS bbddAnimePhix;
USE bbddAnimePhix;

-- Creación de la tabla roles
CREATE TABLE IF NOT EXISTS rol (
	id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL UNIQUE
);

-- Creación de la tabla usuario
CREATE TABLE IF NOT EXISTS usuario (
	id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(60) NOT NULL UNIQUE,
    contrasenia VARCHAR(20) NOT NULL,
    habilitado BOOLEAN NOT NULL DEFAULT true,
    rol_id INT,
    CONSTRAINT fk_rolUsuario_id FOREIGN KEY (rol_id) REFERENCES rol(id_rol) ON DELETE CASCADE
);

-- Creación de la tabla generos
CREATE TABLE IF NOT EXISTS genero (
	id_genero INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    descripcion VARCHAR(100) NOT NULL
);

-- Creación de la tabla estados
CREATE TABLE IF NOT EXISTS estado (
	id_estado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL
);

-- Creación de la tabla anime
CREATE TABLE IF NOT EXISTS anime (
	id_anime INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    descripcion VARCHAR(1000) NOT NULL,
    fechaInicio DATE NOT NULL,
    fechaFin DATE NOT NULL,
    diaSemana INT NOT NULL,
    visible BOOLEAN NOT NULL DEFAULT true,
    genero_id INT,
    estado_id INT,
    CONSTRAINT fk_generoAnime_id FOREIGN KEY (genero_id) REFERENCES genero(id_genero) ON DELETE CASCADE,
    CONSTRAINT fk_estadoAnime_id FOREIGN KEY (estado_id) REFERENCES estado(id_estado) ON DELETE CASCADE
);

-- Creación de la tabla episodio
CREATE TABLE IF NOT EXISTS episodio (
	anime_id INT NOT NULL,
    num_episodio INT NOT NULL,
    url VARCHAR(200),
    PRIMARY KEY (anime_id, num_episodio),
    CONSTRAINT fk_animeEpisodio_id FOREIGN KEY (anime_id) REFERENCES anime(id_anime) ON DELETE CASCADE
);

-- Creación de la tabla intermedia marcar_favoritos
CREATE TABLE IF NOT EXISTS favoritoAnimeUsuario (
	usuario_id INT NOT NULL,
    anime_id INT NOT NULL,
    habilitado BOOLEAN NOT NULL DEFAULT true,
    PRIMARY KEY (usuario_id, anime_id),
    CONSTRAINT fk_usuarioFav_id FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    CONSTRAINT fk_animeFav_id FOREIGN KEY (anime_id) REFERENCES anime(id_anime) ON DELETE CASCADE
);

-- Creación de la tabla intermedia marcar_episodios_vistos
CREATE TABLE IF NOT EXISTS vistoEpisodioUsuario (
	usuario_id INT NOT NULL,
    anime_id INT NOT NULL,
    num_episodio INT NOT NULL,
    habilitado BOOLEAN NOT NULL DEFAULT true,
    PRIMARY KEY (usuario_id, anime_id, num_episodio),
    CONSTRAINT fk_usuarioVisto_id FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    CONSTRAINT fk_episodioVisto_id FOREIGN KEY (anime_id, num_episodio) REFERENCES episodio(anime_id, num_episodio) ON DELETE CASCADE
);

-- Creación de la tabla intermedia comentar_episodio
CREATE TABLE IF NOT EXISTS comentaEpisodioUsuario (
	usuario_id INT NOT NULL,
    anime_id INT NOT NULL,
    num_episodio INT NOT NULL,
    habilitado BOOLEAN NOT NULL DEFAULT true,
    comentario VARCHAR(500),
    PRIMARY KEY (usuario_id, anime_id, num_episodio),
    CONSTRAINT fk_usuarioComenta_id FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    CONSTRAINT fk_episodioComenta_id FOREIGN KEY (anime_id, num_episodio) REFERENCES episodio(anime_id, num_episodio) ON DELETE CASCADE
);

-- Inserciones a la tabla rol
INSERT INTO rol (nombre) VALUES ('ADMIN'), ('USER');

-- Inserciones a la tabla genero
INSERT INTO genero (nombre, descripcion) VALUES
	('Shonen', 'Aventuras y Acción'),
    ('Shojo', 'Romances y Relaciones'),
    ('Seinen', 'Temáticas adultas'),
    ('Josei', 'Realismo y Vida cotidiana'),
    ('Mecha', 'Robots y Ciencia ficción'),
    ('Isekai', 'Otros mundos'),
    ('Horror', 'Psicológico y Sobrenatural');

-- Inserciones a la tabla estado
INSERT INTO estado (nombre) VALUES ('En emisión'), ('Finalizado'), ('Pausado');