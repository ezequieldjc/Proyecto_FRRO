package Data;

import Entities.Productos.ProductoCategoria;
import Entities.Productos.ProductoFabricante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataProductoCategoria {

    public ArrayList<ProductoCategoria> getAll() throws SQLException {
        ArrayList<ProductoCategoria> categorias = new ArrayList<ProductoCategoria>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select id, codigo, nombre, 'desc' from producto_categoria;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            ProductoCategoria pc = new ProductoCategoria();
            pc.setId(rs.getInt("id"));
            pc.setNombre(rs.getString("nombre"));
            pc.setCodigo(rs.getString("codigo"));
            pc.setDesc(rs.getString("desc"));
            categorias.add(pc);
        }
        return categorias;
    }

    public ProductoCategoria getOneByID(ProductoCategoria productoCategoria) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select id, codigo, nombre, 'desc' from producto_categoria where id = ?;");
        stmt.setInt(1,productoCategoria.getId());
        rs = stmt.executeQuery();
        if(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            ProductoCategoria pc = new ProductoCategoria();
            pc.setId(rs.getInt("id"));
            pc.setNombre(rs.getString("nombre"));
            pc.setCodigo(rs.getString("codigo"));
            pc.setDesc(rs.getString("desc"));
            return pc;
        }
        return null;
    }
}
