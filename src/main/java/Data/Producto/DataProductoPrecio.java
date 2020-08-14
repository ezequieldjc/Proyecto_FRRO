package Data.Producto;

import Data.DataConnectioniMac;
import Entities.Productos.Producto;
import Entities.Productos.ProductoFabricante;
import Entities.Productos.ProductoPrecio;
import Entities.Productos.ProductoProveedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataProductoPrecio {

    public ArrayList<ProductoPrecio> getAll() throws SQLException {
        ArrayList<ProductoPrecio> precios = new ArrayList<ProductoPrecio>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select id, idProducto, fechaDesde, fechaHasta, valor, maxDescuento, nombre from producto_precio;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            ProductoPrecio p = new ProductoPrecio();
            p.setId(rs.getInt("id"));
            p.setFechaDesde(rs.getTimestamp("fechaDesde"));
            p.setFechaHasta(rs.getTimestamp("fechaHasta"));
            p.setValor(rs.getFloat("valor"));
            p.setMaxDscto(rs.getFloat("maxDescuento"));
            p.setNombre(rs.getString("nombre"));
            precios.add(p);
        }
        return precios;
    }

    public ArrayList<ProductoPrecio> getAllByProducto(Producto producto) throws SQLException {
        ArrayList<ProductoPrecio> precios = new ArrayList<ProductoPrecio>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select id, idProducto, fechaDesde, fechaHasta, valor, maxDescuento, nombre from producto_precio where idProducto = ? order by fechaHasta desc;");
        stmt.setInt(1,producto.getId());
        rs = stmt.executeQuery();
        while(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            ProductoPrecio p = new ProductoPrecio();
            p.setId(rs.getInt("id"));
            p.setFechaDesde(rs.getTimestamp("fechaDesde"));
            p.setFechaHasta(rs.getTimestamp("fechaHasta"));
            p.setValor(rs.getFloat("valor"));
            p.setMaxDscto(rs.getFloat("maxDescuento"));
            p.setNombre(rs.getString("nombre"));
            precios.add(p);
        }
        return precios;
    }

    public ArrayList<ProductoPrecio> getActivosByProducto(Producto producto) throws SQLException {
        ArrayList<ProductoPrecio> precios = new ArrayList<ProductoPrecio>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select id, idProducto, fechaDesde, fechaHasta, valor, maxDescuento, nombre from producto_precio where idProducto = ? and fechaDesde is not null;");
        stmt.setInt(1,producto.getId());
        rs = stmt.executeQuery();
        while(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            ProductoPrecio p = new ProductoPrecio();
            p.setId(rs.getInt("id"));
            p.setFechaDesde(rs.getTimestamp("fechaDesde"));
            p.setFechaHasta(rs.getTimestamp("fechaHasta"));
            p.setValor(rs.getFloat("valor"));
            p.setMaxDscto(rs.getFloat("maxDescuento"));
            p.setNombre(rs.getString("nombre"));
            precios.add(p);
        }
        return precios;
    }

}
