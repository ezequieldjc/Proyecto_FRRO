package Controladores;

import Data.DataAccion;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaAccion;

import java.sql.SQLException;

public class TieneAccionPermitida {

    public boolean tieneAccionPermitida (PersonaEmpleado e, SistemaAccion a){
        try{
            return new DataAccion().estaPermitido(e,a);
        } catch (SQLException ef){
            ef.printStackTrace();
            return false;
        }
    }

    public boolean tieneAccionPermitidaByCodigoAccion (PersonaEmpleado e, SistemaAccion a){
        try{
            return new DataAccion().estaPermitidoByCodigoAccion(e,a);
        } catch (SQLException ef){
            ef.printStackTrace();
            return false;
        }
    }

}
