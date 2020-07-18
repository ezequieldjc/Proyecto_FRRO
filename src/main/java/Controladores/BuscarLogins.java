package Controladores;

import Data.DataLogLogins;
import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaLogLogins;

import java.sql.SQLException;
import java.util.ArrayList;

public class BuscarLogins {

    public ArrayList<SistemaLogLogins> buscarLogins(String user,  String resultado){
        try {
        if (user.equals("") && resultado.equals(""))
            return new DataLogLogins().getAll();
        else {
            SistemaLogLogins l = new SistemaLogLogins();
            if (!(user.equals("")))
                l.setUsuario(user);
            if (!(resultado.equals("")))
                l.setResultado(resultado.equals("1"));
            return new DataLogLogins().getByFilter(l);
        }
        } catch (SQLException ef){
            ef.printStackTrace();
            return null;
        }
    }
    /*
    pblic PersonaEmpleado buscarEmpleadoByUsuario (PersonaEmpleado e){
        try {
            return new DataPersonaEmpleado().getByUsuario(e);
        } catch (SQLException ef){
            ef.printStackTrace();
            return null;
        }
    }*/
}
