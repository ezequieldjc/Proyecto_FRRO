package Controladores;

import Data.DataPersonaEmpleado;
import Data.DataSesiones;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaSesiones;

import java.sql.SQLException;
import java.util.ArrayList;

public class BuscarSesiones {

    public ArrayList<SistemaSesiones> buscarSesiones(String id, String nya, String estado){
        try {
            if (id.equals("") && nya.equals("") && estado.equals(""))
                return new DataSesiones().getSesiones();
            else
                return new DataSesiones().getSesionesByFilter(id, nya,estado);
        } catch (SQLException ef){
            ef.printStackTrace();
            return null;
        }
    }



}
