package Controladores.Producto;

import Controladores.GrabarNotificacion;
import Data.Producto.DataProductoPrecio;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.Productos.Producto;
import Entities.Productos.ProductoPrecio;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoPrecioHandler {

    public ArrayList<ProductoPrecio> getAll(){
        try {
            return new DataProductoPrecio().getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ArrayList<ProductoPrecio> getAllByProducto(Producto p){
        try {
            return new DataProductoPrecio().getAllByProducto(p);
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(5));
            n.setMensaje("Excepcion ocurrida al intentar obtener los precios de: "  + p.getCodigo() );
            n.setPrioridad(1);
            new GrabarNotificacion(n);
            return null;
        }
    }

    public ArrayList<ProductoPrecio> getActivosByProducto(Producto p){
        try {
            return new DataProductoPrecio().getActivosByProducto(p);
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(5));
            n.setMensaje("Excepcion ocurrida al intentar obtener los precios activos de: "  + p.getCodigo() );
            n.setPrioridad(1);
            new GrabarNotificacion(n);
            return null;
        }
    }
}
