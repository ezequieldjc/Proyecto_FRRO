package Controladores;

import Data.DataLogLogins;
import Data.DataSesiones;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaLogLogins;
import Entities.System.SistemaSesiones;

import java.sql.SQLException;

public class ManejoSesiones {

    public ManejoSesiones(){

    }

    public ManejoSesiones (PersonaEmpleado e){
        //el constructor genera la nueva sesion
        new DataSesiones().registrarNuevaSesion(e);
    }

    public void cerrarSesiones (PersonaEmpleado e){
        try{
            new DataSesiones().cerrarSesionesPorEmpleado(e);
        } catch (SQLException ef){
            ef.printStackTrace();
        }
    }

    public Boolean tieneSesionAbierta (PersonaEmpleado e){
        try {
            return new DataSesiones().tieneSesionAbierta(e);
        } catch (SQLException ef){
            ef.printStackTrace();
            return null;
        }
    }

    public int cantSesionesAbiertas (PersonaEmpleado e){
        try {
            return new DataSesiones().cantSesionesAbiertas(e);
        } catch (SQLException ef){
            ef.printStackTrace();
            return -1;
        }
    }

    public void limpiarSesion (PersonaEmpleado e){
        this.cerrarSesiones(e);
    }


}
