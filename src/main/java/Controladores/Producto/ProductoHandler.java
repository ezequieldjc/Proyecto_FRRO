package Controladores.Producto;

import Controladores.GrabarNotificacion;
import Data.Producto.DataProducto;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.Productos.Producto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoHandler {

    public ArrayList<Producto> getAll(){
        try {
            return new DataProducto().getAll();
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(5));
            n.setMensaje("Excepcion ocurrida al intentar obtener los productos" );
            n.setPrioridad(1);
            new GrabarNotificacion(n);
            return null;
        }
    }

    public Producto getOneByID (Producto producto){
        try {
            return new DataProducto().getOneByID(producto);
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(5));
            n.setMensaje("Excepcion ocurrida al intentar obtener al producto : " + producto.getCodigo() );
            n.setPrioridad(1);
            new GrabarNotificacion(n);
            return null;
        }
    }

    public Producto registrarProducto(Producto p ){
        try {
            return new DataProducto().registrarProducto(p);
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(5));
            n.setMensaje("Excepcion ocurrida al intentar ingresar al producto : " + p.getCodigo() );
            n.setPrioridad(1);
            new GrabarNotificacion(n);
            return null;
        }
    }

}
