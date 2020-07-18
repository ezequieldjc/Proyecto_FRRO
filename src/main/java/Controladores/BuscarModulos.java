package Controladores;

import Data.DataSistemaModulo;

import java.sql.SQLException;
import java.util.HashMap;

public class BuscarModulos {

    public HashMap<Integer,String> getAllCodigos(){
        try {
            return new DataSistemaModulo().getAllCodigos();
        } catch (SQLException ef){
            ef.printStackTrace();
            return null;
        }
    }

}
