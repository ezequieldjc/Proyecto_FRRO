package Controladores;

import Data.DataPerfil;
import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaPerfil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class BuscarPerfiles {

    public ArrayList<PersonaPerfil> buscarTodos (PersonaPerfil p ){
        try {
            return new DataPerfil().getAllByFilter(p);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public HashMap<Integer, String> getHashID(){
        try {
            return new DataPerfil().getHashNombres();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
