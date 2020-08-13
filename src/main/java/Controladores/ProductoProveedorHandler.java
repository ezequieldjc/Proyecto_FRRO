package Controladores;

import Data.DataProductoFabricante;
import Data.DataProductoProveedor;
import Entities.Productos.ProductoFabricante;
import Entities.Productos.ProductoProveedor;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoProveedorHandler {

    public ArrayList<ProductoProveedor> getAll() throws SQLException {
        try{
            return new DataProductoProveedor().getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ProductoProveedor getOneByID(ProductoProveedor productoProveedor){
        try {
            return new DataProductoProveedor().getOneByID(productoProveedor);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
