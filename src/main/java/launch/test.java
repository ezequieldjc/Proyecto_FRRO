package launch;

import Controladores.BuscarNombresAvatar;
import Controladores.BuscarSesiones;
import Controladores.HashearPassword;
import Controladores.VerificarLogin;
import Data.DataGlobalConfig;
import Data.DataNotificacion;
import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaSesiones;

import java.sql.SQLException;
import java.util.ArrayList;

public class test {

    public static void main(String[] args) throws SQLException {



        ArrayList<String> a = new BuscarNombresAvatar().buscarPNGNames();
        for (String f : a ){
            System.out.println(f);
        }



    }

}
