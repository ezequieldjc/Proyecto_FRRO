package Controladores;

import Data.DataAccion;
import Data.DataBoton;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaAccion;
import Entities.System.SistemaBoton;

import java.sql.SQLException;

public class TienePaginaPermitida {

    public boolean tienePaginaPermitida (PersonaEmpleado e, SistemaBoton b){
        try{
            return new DataBoton().estaPermitido(e,b);
        } catch (SQLException ef){
            ef.printStackTrace();
            return false;
        }
    }

}
