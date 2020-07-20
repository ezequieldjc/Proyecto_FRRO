package launch;

import Controladores.BuscarSesiones;
import Controladores.HashearPassword;
import Controladores.VerificarLogin;
import Data.DataGlobalConfig;
import Data.DataNotificacion;
import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaSesiones;

import java.sql.SQLException;

public class test {

    public static void main(String[] args) throws SQLException {

        PersonaEmpleado e = new VerificarLogin().esEmpleadoValido(new PersonaEmpleado("AD99999","abcd1234"));

        if(e!=null){System.out.println("Distinto nulo");}
        else { System.out.println("Es nulo");}

        //System.out.println(new HashearPassword().unHidePwd("mTNHPaDcxlJQda94DcQduIQEJ"));

        for (SistemaSesiones s : new BuscarSesiones().buscarSesiones("","","")) {
            System.out.println(s.getId());

        }


    }

}
