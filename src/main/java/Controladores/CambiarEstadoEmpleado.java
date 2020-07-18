package Controladores;

import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaMensaje;
import Entities.System.SistemaModulo;

import java.sql.SQLException;

public class CambiarEstadoEmpleado {

    public boolean cambiarEstadoEmpleado(PersonaEmpleado e){
        try {
            return new DataPersonaEmpleado().cambiarEstado(e);
        } catch (SQLException ef){
            ef.printStackTrace();
            return false;
        }
    }
}
