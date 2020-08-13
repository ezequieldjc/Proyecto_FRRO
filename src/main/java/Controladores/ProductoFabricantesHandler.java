package Controladores;

import Data.DataConnectioniMac;
import Data.DataProductoFabricante;
import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;
import Entities.Productos.ProductoFabricante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
