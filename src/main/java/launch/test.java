package launch;

import Data.DataGlobalConfig;
import Data.DataNotificacion;
import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;

import java.sql.SQLException;

public class test {

    public static void main(String[] args) throws SQLException {

        PersonaEmpleado e = new PersonaEmpleado();
        e.setId(1);
        //new DataNotificacion().getAllByUsuario(e);
        //new DataNotificacion().getUnreadByUsuario(e);
        for (PersonaEmpleado f : new DataPersonaEmpleado().getAll()){
            System.out.println(f.getCelular());
        }


    }

}
