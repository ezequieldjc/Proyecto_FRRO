package Data.Producto;

import Data.DataConnectioniMac;
import Entities.Productos.ProductoFabricante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataProductoFabricante {

    public ArrayList<ProductoFabricante> getAll() throws SQLException {
        ArrayList<ProductoFabricante> fabricantes = new ArrayList<ProductoFabricante>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select id, codigo, nombre, cuit from producto_fabricante;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            ProductoFabricante f = new ProductoFabricante();
            f.setId(rs.getInt("id"));
            f.setCodigo(rs.getString("codigo"));
            f.setCuit(rs.getString("cuit"));
            f.setNombre(rs.getString("nombre"));
            fabricantes.add(f);
        }
        return fabricantes;
    }

    public ProductoFabricante getOneByID(ProductoFabricante pf) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select id, codigo, nombre, cuit from producto_fabricante where id = ?;");
        stmt.setInt(1,pf.getId());
        rs = stmt.executeQuery();
        if(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            ProductoFabricante f = new ProductoFabricante();
            f.setId(rs.getInt("id"));
            f.setCodigo(rs.getString("codigo"));
            f.setCuit(rs.getString("cuit"));
            f.setNombre(rs.getString("nombre"));
            return f;
        }
        return null;
    }
}
