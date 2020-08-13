package Controladores;

import Data.DataProductoPrecio;
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
            throwables.printStackTrace();
            return null;
        }
    }

    public ArrayList<ProductoPrecio> getActivosByProducto(Producto p){
        try {
            return new DataProductoPrecio().getActivosByProducto(p);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
