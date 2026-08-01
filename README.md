# Sistema de Inventario

Este programa sirve para guardar productos. Se puede poner el nombre, precio, cantidad y categoría de cada producto.

También permite agregar, editar, buscar y borrar productos. Las categorías se pueden administrar desde el botón de la ventana principal.

## Cómo abrirlo

1. Abrir la carpeta del proyecto en IntelliJ.
2. Abrir el archivo `pom.xml` como proyecto Maven.
3. Ejecutar la clase `App.java`.

También se puede ejecutar desde la terminal con:

```bash
mvn clean compile exec:java
```

La primera vez necesita Internet para descargar SQLite. Después se crea sola la carpeta `data` con la base de datos.

## Carpetas principales

- `model`: clases de Producto y Categoria.
- `dao`: consultas para guardar y leer datos.
- `ui`: ventanas del programa.
- `database`: script de la base de datos con ejemplos.

## Nota

Si aparecen mensajes amarillos de SQLite o Java en la consola, no pasa nada mientras abra la ventana del programa. No afectan el funcionamiento.

Para subirlo a Git cuando toque:

```bash
git init
git add .
git commit -m "Primer avance del inventario"
```
