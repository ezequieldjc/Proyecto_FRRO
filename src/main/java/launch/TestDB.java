package launch;

import Controladores.BuscarGlobalConfig;
import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaGlobalConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class TestDB {

    public void main (String args[]){

        boolean flag = true;
        String pattern = new BuscarGlobalConfig().buscarGlobalConfigByCodigo(new SistemaGlobalConfig("USR_PTRN")).getValorAtributo();
        try {
            ArrayList<PersonaEmpleado> empleados =  new DataPersonaEmpleado().getAll();
            for (PersonaEmpleado e : empleados){
                if (!(Pattern.matches(pattern, "ED12345")))
                    flag=false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
