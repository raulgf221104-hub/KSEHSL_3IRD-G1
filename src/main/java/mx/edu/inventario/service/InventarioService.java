package mx.edu.inventario.service;

import java.util.List; import mx.edu.inventario.dao.*; import mx.edu.inventario.exception.*; import mx.edu.inventario.model.*;

/** Aplica reglas de negocio antes de persistir datos. */
public class InventarioService {
    private final CategoriaDAO categorias=new CategoriaDAO(); private final ProductoDAO productos=new ProductoDAO();
    public List<Categoria> categorias()throws DataAccessException{return categorias.listar();} public List<Producto> productos(String f)throws DataAccessException{return productos.listar(f);}
    public void guardarCategoria(Categoria c)throws ValidationException,DataAccessException{if(c.getNombre()==null||c.getNombre().trim().isEmpty())throw new ValidationException("El nombre de la categoría es obligatorio.");if(c.getId()==null)categorias.guardar(c);else categorias.actualizar(c);}
    public void eliminarCategoria(int id)throws DataAccessException{categorias.eliminar(id);}
    public void guardarProducto(Producto p)throws ValidationException,DataAccessException{if(p.getNombre()==null||p.getNombre().trim().isEmpty())throw new ValidationException("El nombre del producto es obligatorio.");if(p.getPrecio()<0)throw new ValidationException("El precio no puede ser negativo.");if(p.getExistencia()<0)throw new ValidationException("La existencia no puede ser negativa.");if(p.getCategoria()==null)throw new ValidationException("Seleccione una categoría.");if(p.getId()==null)productos.guardar(p);else productos.actualizar(p);}
    public void eliminarProducto(int id)throws DataAccessException{productos.eliminar(id);}
}