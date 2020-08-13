package launch;

import Controladores.ProductoFabricantesHandler;
import Entities.Productos.ProductoFabricante;

public class TestProductos {

    public static void main(String[] args){
        for (ProductoFabricante p : new ProductoFabricantesHandler().getAll()) {

        }
    }
}
