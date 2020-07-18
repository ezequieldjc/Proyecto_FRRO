package Controladores;

import Data.DataSistemaMensaje;
import Entities.System.SistemaMensaje;

import java.sql.SQLException;

public class GrabarMensaje {
    public GrabarMensaje(SistemaMensaje m){
        try {
            new DataSistemaMensaje(m);
        } catch (SQLException ef){
            ef.printStackTrace();
        }
    }

}
