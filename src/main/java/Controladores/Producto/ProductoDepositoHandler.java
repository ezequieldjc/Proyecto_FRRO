package Controladores.Producto;

import Controladores.GrabarNotificacion;
import Data.Producto.DataProductoDeposito;
import Data.Producto.DataProductoFabricante;
import Data.Producto.DataProductoUnidadMedida;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.Productos.ProductoFabricante;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductoDepositoHandler {

    public ArrayList<ProductoFabricante> getAll() {
        try{
            return new DataProductoFabricante().getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ProductoFabricante getOneByID(ProductoFabricante productoFabricante){
        try {
            return new DataProductoFabricante().getOneByID(productoFabricante);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public HashMap<Integer, String> getHash(){
        try {
            return new DataProductoDeposito().getHashNombres();
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(5));
            n.setMensaje("Excepcion ocurrida al intentar obtener el hash de los depositos ");
            n.setPrioridad(1);
            new GrabarNotificacion(n);
            return null;
        }
    }
}
