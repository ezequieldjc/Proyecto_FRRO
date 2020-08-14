package Data;

import Entities.Productos.ProductoFabricante;
import Entities.Productos.ProductoPrecio;
import Entities.Productos.ProductoProveedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataProductoProveedor {

    public ArrayList<ProductoProveedor> getAll() throws SQLException {
        ArrayList<ProductoProveedor> proveedores = new ArrayList<ProductoProveedor>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select id, codigo, nombre, cuit from producto_proveedor;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            ProductoProveedor p = new ProductoProveedor();
            p.setId(rs.getInt("id"));
            p.setCodigo(rs.getString("codigo"));
            p.setCuit(rs.getString("cuit"));
            p.setNombre(rs.getString("nombre"));
            proveedores.add(p);
        }
        return proveedores;
    }

    public ProductoProveedor getOneByID(ProductoProveedor pp) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select id, codigo, nombre, cuit from producto_proveedor where id = ?;");
        stmt.setInt(1,pp.getId());
        rs = stmt.executeQuery();
        if(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            ProductoProveedor p = new ProductoProveedor();
            p.setId(rs.getInt("id"));
            p.setCodigo(rs.getString("codigo"));
            p.setCuit(rs.getString("cuit"));
            p.setNombre(rs.getString("nombre"));
            return p;
        }
        return null;
    }
}
