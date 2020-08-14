package Controladores.Producto;

import Controladores.GrabarNotificacion;
import Data.Producto.DataProductoProveedor;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.Productos.ProductoProveedor;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoProveedorHandler {

    public ArrayList<ProductoProveedor> getAll() throws SQLException {
        try{
            return new DataProductoProveedor().getAll();
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(5));
            n.setMensaje("Excepcion ocurrida al intentar obtener los proveedor: ");
            n.setPrioridad(1);
            new GrabarNotificacion(n);
            return null;
        }
    }

    public ProductoProveedor getOneByID(ProductoProveedor productoProveedor){
        try {
            return new DataProductoProveedor().getOneByID(productoProveedor);
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(5));
            n.setMensaje("Excepcion ocurrida al intentar obtener el proveedor: " + productoProveedor.getCodigo());
            n.setPrioridad(1);
            new GrabarNotificacion(n);
            return null;
        }
    }
}
