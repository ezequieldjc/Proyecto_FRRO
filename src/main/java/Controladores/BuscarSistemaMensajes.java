package Controladores;

import Data.DataSistemaMensaje;
import Entities.System.SistemaMensaje;

import java.sql.SQLException;
import java.util.ArrayList;

public class BuscarSistemaMensajes {

    public ArrayList<SistemaMensaje> buscarSistemaMensajes(SistemaMensaje m){
        try{
            return new DataSistemaMensaje().getByFilter(m);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
