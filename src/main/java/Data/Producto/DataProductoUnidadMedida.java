package Data.Producto;

import Data.DataConnectioniMac;
import Entities.Productos.ProductoProveedor;
import Entities.Productos.ProductoUnidadMedida;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DataProductoUnidadMedida {

    public ProductoUnidadMedida getOneByID(ProductoUnidadMedida productoUnidadMedida) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select id, codigo, nombre, cuit from producto_unidadMedida where id = ?;");
        stmt.setInt(1,productoUnidadMedida.getId());
        rs = stmt.executeQuery();
        if(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            ProductoUnidadMedida um = new ProductoUnidadMedida();
            um.setId(rs.getInt("id"));
            um.setCodigo(rs.getString("codigo"));
            um.setDesc(rs.getString("desc"));
            um.setNombre(rs.getString("nombre"));
            return um;
        }
        return null;
    }

    public HashMap<Integer, String> getHashNombres () throws SQLException {
        HashMap<Integer, String> depositos = new HashMap<Integer, String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select id, nombre from producto_unidadMedida";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()){
            depositos.put(rs.getInt("id"), rs.getString("nombre"));
        }
        return depositos;
    }
}
