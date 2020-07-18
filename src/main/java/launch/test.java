package launch;

import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;

import java.sql.SQLException;

public class test {

    public static void main(String[] args) throws SQLException {

        for (PersonaEmpleado p : new DataPersonaEmpleado().getAll()){
            System.out.println(p.getApellido());
        }



    }

}
