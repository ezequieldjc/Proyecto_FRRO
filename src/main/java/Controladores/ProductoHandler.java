package Controladores;

import Data.DataProducto;
import Entities.Productos.Producto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoHandler {

    public ArrayList<Producto> getAll(){
        try {
            return new DataProducto().getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

}
