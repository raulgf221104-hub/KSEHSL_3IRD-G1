package mx.edu.inventario.db;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import mx.edu.inventario.exception.DataAccessException;

/** Centraliza la conexión SQLite reutilizable y crea el esquema base. */
public final class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:data/inventario.db";
    private DatabaseConnection() { }
    public static Connection getConnection() throws DataAccessException {
        try { return DriverManager.getConnection(URL); }
        catch (SQLException e) { throw new DataAccessException("No se pudo conectar con la base de datos.", e); }
    }
    public static void initializeDatabase() throws DataAccessException {
        try {
            Files.createDirectories(Path.of("data"));
            try (Connection c = getConnection(); Statement st = c.createStatement()) {
                st.execute("PRAGMA foreign_keys = ON");
                st.executeUpdate("CREATE TABLE IF NOT EXISTS categorias (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL UNIQUE, descripcion TEXT)");
                st.executeUpdate("CREATE TABLE IF NOT EXISTS productos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, precio REAL NOT NULL CHECK(precio >= 0), existencia INTEGER NOT NULL CHECK(existencia >= 0), categoria_id INTEGER NOT NULL, FOREIGN KEY(categoria_id) REFERENCES categorias(id) ON DELETE RESTRICT)");
            }
        } catch (Exception e) { if (e instanceof DataAccessException d) throw d; throw new DataAccessException("No se pudo preparar la base de datos.", e); }
    }
}