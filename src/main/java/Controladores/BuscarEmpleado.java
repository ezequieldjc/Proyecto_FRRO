package Controladores;

import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class BuscarEmpleado {

    public ArrayList<PersonaEmpleado> buscarEmpleados(String user, String perfil, String estado){
        try {
        if (user.equals("") && perfil.equals("") && estado.equals(""))
            return new DataPersonaEmpleado().getAll();
        else
            return new DataPersonaEmpleado().getAllByFilter(user, perfil,estado);
        } catch (SQLException ef){
            ef.printStackTrace();
            return null;
        }
    }

    public PersonaEmpleado buscarEmpleadoByUsuario (PersonaEmpleado e){
        try {
            return new DataPersonaEmpleado().getByUsuario(e);
        } catch (SQLException ef){
            ef.printStackTrace();
            return null;
        }
    }

    public PersonaEmpleado buscarEmpleadoByID (PersonaEmpleado e){
        try {
            return new DataPersonaEmpleado().getByUsuario(e);
        } catch (SQLException ef){
            ef.printStackTrace();
            return null;
        }
    }

    public HashMap<Integer, String> buscarEmpleadosUser (){
        try {
            return new DataPersonaEmpleado().getAllNombresUsuarios();
        } catch (SQLException ef){
            ef.printStackTrace();
            return null;
        }
    }
}
