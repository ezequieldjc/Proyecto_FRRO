package UTest;

import Controladores.BuscarGlobalConfig;
import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaGlobalConfig;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class TestUserID extends TestCase {


    public void testUserID(){
        //Este test revisa que todos los user de la tabla persona_empleado sean coherentes al pattern establecido para los mismos
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
        assertEquals(true, flag);
    }


}
