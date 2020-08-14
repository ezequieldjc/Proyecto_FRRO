package Controladores.Producto;

import Controladores.GrabarNotificacion;
import Data.Producto.DataProductoStock;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.Productos.Producto;
import Entities.Productos.ProductoStock;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoStockHandler {

    public ArrayList<ProductoStock> getAllByProducto(Producto producto){
        try {
            return new DataProductoStock().getAllByProducto(producto);
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(5));
            n.setMensaje("Excepcion ocurrida al intentar obtener el stock del produco " + producto.getCodigo());
            n.setPrioridad(1);
            new GrabarNotificacion(n);
            return null;
        }
    }
}
