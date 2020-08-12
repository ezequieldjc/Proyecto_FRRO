package UTest;

import Controladores.BuscarGlobalConfig;
import Data.DataConnectioniMac;
import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaGlobalConfig;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class TestDBConecction extends TestCase {


    public void testDBConnection() {
        Connection con = DataConnectioniMac.getInstancia().getConn();
        if (con != null)
            assertEquals(true, true);
        else
            assertEquals(false, true);
    }


}
