package Data.Producto;

import Data.DataConnectioniMac;
import Entities.Productos.ProductoDeposito;
import Entities.Productos.ProductoFabricante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataProductoDeposito {

    public ArrayList<ProductoDeposito> getAll() throws SQLException {
        ArrayList<ProductoDeposito> depositos = new ArrayList<ProductoDeposito>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select id, codigo, nombre, direccion, telefono from producto_deposito;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            ProductoDeposito d = new ProductoDeposito();
            d.setId(rs.getInt("id"));
            d.setCodigo(rs.getString("codigo"));
            d.setDireccion(rs.getString("direccion"));
            d.setNombre(rs.getString("nombre"));
            d.setTelefono(rs.getString("telefono"));
            depositos.add(d);
        }
        return depositos;
    }

    public HashMap<Integer, String> getHashNombres () throws SQLException {
        HashMap<Integer, String> depositos = new HashMap<Integer, String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select id, codigo from producto_deposito";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()){
            depositos.put(rs.getInt("id"), rs.getString("codigo"));

        }
        return depositos;
    }
}
