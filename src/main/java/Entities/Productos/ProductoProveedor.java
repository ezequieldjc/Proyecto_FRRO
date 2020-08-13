package Entities.Productos;

import Entities.Empresa;

public class ProductoProveedor extends Empresa {

    public ProductoProveedor(){

    }
    public ProductoProveedor(int id, String codigo, String nombre, String cuit) {
        super(id, codigo, nombre, cuit);
    }

    public ProductoProveedor(String cuit) {
        super(cuit);
    }

    public ProductoProveedor(int id) {
        super(id);
    }
}
