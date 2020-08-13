package Entities.Productos;

import Entities.Empresa;

public class ProductoFabricante extends Empresa {

    public ProductoFabricante(){

    }

    public ProductoFabricante(int id, String codigo, String nombre, String cuit) {
        super(id, codigo, nombre, cuit);
    }

    public ProductoFabricante(String cuit) {
        super(cuit);
    }

    public ProductoFabricante(int id) {
        super(id);
    }
}
