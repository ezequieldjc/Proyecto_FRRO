package Controladores.Producto;

import Controladores.GrabarNotificacion;
import Data.Producto.DataProductoUnidadMedida;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.Productos.ProductoUnidadMedida;

import java.sql.SQLException;
import java.util.HashMap;

public class ProductoUnidadMedidaHandler {

    public ProductoUnidadMedida getOneByID (ProductoUnidadMedida productoUnidadMedida){
        try {
            return new DataProductoUnidadMedida().getOneByID(productoUnidadMedida);
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(5));
            n.setMensaje("Excepcion ocurrida al intentar obtener la unidad de Medida de " + productoUnidadMedida.getCodigo());
            n.setPrioridad(1);
            new GrabarNotificacion(n);
            return null;
        }
    }

    public HashMap<Integer, String> getHash(){
        try {
            return new DataProductoUnidadMedida().getHashNombres();
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(5));
            n.setMensaje("Excepcion ocurrida al intentar obtener el hash de las unidades de medida ");
            n.setPrioridad(1);
            new GrabarNotificacion(n);
            return null;
        }
    }
}
