package launch;

import Controladores.ProductoFabricantesHandler;
import Controladores.ProductoHandler;
import Entities.Productos.Producto;
import Entities.Productos.ProductoFabricante;

public class TestProductos {

    public static void main(String[] args){
        for (Producto p : new ProductoHandler().getAll()){
            System.out.println(p.getNombre());
        }
    }
}
