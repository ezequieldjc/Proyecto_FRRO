package Controladores.Producto;

import Data.Producto.DataProductoFabricante;
import Entities.Productos.ProductoFabricante;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoFabricantesHandler {

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
}
