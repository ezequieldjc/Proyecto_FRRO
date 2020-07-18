package Terminal;

import Auxiliares.ComparaAcciones;
import Controladores.*;
import Data.DataPerfil;
import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;
import Entities.System.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Test {
    public static void main (String[] args) throws SQLException {

       PersonaPerfil p = new DataPerfil().getOneByID_v2(new PersonaPerfil(3));
       System.out.println(p.getOwner().getId());

    }
}
