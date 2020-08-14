package Controladores.Producto;

import Data.Producto.DataProductoCategoria;
import Entities.Productos.ProductoCategoria;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoCategoriaHandler {

    public ArrayList<ProductoCategoria> getAll(){
        try {
            return new DataProductoCategoria().getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ProductoCategoria getOneByID(ProductoCategoria productoCategoria){
        try {
            return new DataProductoCategoria().getOneByID(productoCategoria);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
