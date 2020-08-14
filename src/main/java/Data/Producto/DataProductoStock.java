package Data.Producto;

import Controladores.Producto.ProductoUnidadMedidaHandler;
import Data.DataConnectioniMac;
import Entities.Productos.Producto;
import Entities.Productos.ProductoFabricante;
import Entities.Productos.ProductoStock;
import Entities.Productos.ProductoUnidadMedida;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataProductoStock {

    public ArrayList<ProductoStock> getAllByProducto(Producto producto) throws SQLException {
        ArrayList<ProductoStock> productoStocks = new ArrayList<ProductoStock>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                "select id, idDeposito, idUnidad, stockMin, stockMax, stockActual from producto_producto_deposito where idProducto = ?;");
        stmt.setInt(1,producto.getId());
        rs = stmt.executeQuery();
        while(rs.next()) {
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            ProductoStock ps = new ProductoStock();
            ps.setId(rs.getInt("id"));
            ps.setIdDeposito(rs.getInt("idDeposito"));
            //ps.setProductoUnidadMedida(new ProductoUnidadMedidaHandler().getOneByID(new ProductoUnidadMedida(rs.getInt("idUnidad"))));
            ps.setStockActual(rs.getFloat("stockActual"));
            ps.setStockMaximo(rs.getFloat("stockMax"));
            ps.setStockMinimo(rs.getFloat("stockMin"));
            productoStocks.add(ps);
        }
        if (productoStocks.isEmpty())
            return null;
        return productoStocks;
    }

}
