PRAGMA foreign_keys = ON;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS categorias;

CREATE TABLE categorias (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  nombre TEXT NOT NULL UNIQUE,
  descripcion TEXT
);

CREATE TABLE productos (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  nombre TEXT NOT NULL,
  precio REAL NOT NULL CHECK(precio >= 0),
  existencia INTEGER NOT NULL CHECK(existencia >= 0),
  categoria_id INTEGER NOT NULL,
  FOREIGN KEY (categoria_id) REFERENCES categorias(id) ON DELETE RESTRICT
);

INSERT INTO categorias(nombre, descripcion) VALUES
('Papelería', 'Materiales escolares y de oficina'),
('Tecnología', 'Accesorios y equipos electrónicos'),
('Limpieza', 'Productos para higiene y limpieza');

INSERT INTO productos(nombre, precio, existencia, categoria_id) VALUES
('Cuaderno profesional', 58.50, 25, 1),
('Memoria USB 64 GB', 189.00, 10, 2),
('Desinfectante 1 L', 42.00, 18, 3);
