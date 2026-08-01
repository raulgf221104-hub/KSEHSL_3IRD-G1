package mx.edu.inventario.dao;

import java.sql.*; import java.util.*;
import mx.edu.inventario.db.DatabaseConnection; import mx.edu.inventario.exception.DataAccessException; import mx.edu.inventario.model.*;

public class ProductoDAO {
    private static final String BASE="SELECT p.id,p.nombre,p.precio,p.existencia,c.id categoria_id,c.nombre categoria,c.descripcion FROM productos p JOIN categorias c ON c.id=p.categoria_id ";
    public List<Producto> listar(String criterio) throws DataAccessException { List<Producto> out=new ArrayList<>(); String sql=BASE+"WHERE lower(p.nombre) LIKE lower(?) ORDER BY p.id DESC"; try(Connection cn=DatabaseConnection.getConnection();PreparedStatement ps=cn.prepareStatement(sql)){ps.setString(1,"%"+(criterio==null?"":criterio.trim())+"%");try(ResultSet rs=ps.executeQuery()){while(rs.next())out.add(map(rs));}}catch(SQLException e){throw new DataAccessException("No se pudieron consultar los productos.",e);}return out; }
    public void guardar(Producto p)throws DataAccessException{String s="INSERT INTO productos(nombre,precio,existencia,categoria_id) VALUES(?,?,?,?)";execute(p,s,false);}
    public void actualizar(Producto p)throws DataAccessException{String s="UPDATE productos SET nombre=?,precio=?,existencia=?,categoria_id=? WHERE id=?";execute(p,s,true);}
    private void execute(Producto p,String s,boolean update)throws DataAccessException{try(Connection cn=DatabaseConnection.getConnection();PreparedStatement ps=cn.prepareStatement(s)){ps.setString(1,p.getNombre());ps.setDouble(2,p.getPrecio());ps.setInt(3,p.getExistencia());ps.setInt(4,p.getCategoria().getId());if(update)ps.setInt(5,p.getId());if(ps.executeUpdate()==0)throw new DataAccessException("El producto ya no existe.");}catch(SQLException e){throw new DataAccessException("No se pudo guardar el producto.",e);}}
    public void eliminar(int id)throws DataAccessException{try(Connection cn=DatabaseConnection.getConnection();PreparedStatement ps=cn.prepareStatement("DELETE FROM productos WHERE id=?")){ps.setInt(1,id);ps.executeUpdate();}catch(SQLException e){throw new DataAccessException("No se pudo eliminar el producto.",e);}}
    private Producto map(ResultSet r)throws SQLException{return new Producto(r.getInt("id"),r.getString("nombre"),r.getDouble("precio"),r.getInt("existencia"),new Categoria(r.getInt("categoria_id"),r.getString("categoria"),r.getString("descripcion")));}
}