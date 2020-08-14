package launch;


import Controladores.Producto.ProductoHandler;
import Data.Producto.DataProducto;
import Data.Producto.DataProductoDeposito;
import Data.Producto.DataProductoUnidadMedida;
import Entities.Productos.Producto;
import Entities.Productos.ProductoDeposito;
import Entities.Productos.ProductoStock;
import Entities.Productos.ProductoUnidadMedida;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TestProductos {

    public static void main(String[] args){

        Producto p = new Producto();
        ProductoStock ps = new ProductoStock();

        ps.setStockActual(14);
        ps.setStockMinimo(12);
        ps.setStockMaximo(33);
        ps.setIdDeposito(1);
        ps.setProductoUnidadMedida(new ProductoUnidadMedida(1));
        ArrayList<ProductoStock> pss = new ArrayList<ProductoStock>();
        pss.add(ps);
        p.setProductoStock(pss);
        try {
            new DataProducto().registrarStockInicial(p);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
