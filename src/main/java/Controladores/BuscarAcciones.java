package Controladores;

import Data.DataAccion;
import Data.DataBoton;
import Entities.Notificaciones.Notificacion;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaAccion;
import Entities.System.SistemaBoton;

import java.sql.SQLException;
import java.util.ArrayList;

public class BuscarAcciones {

    public ArrayList<SistemaAccion> buscarAcciones(PersonaEmpleado e){
        try {
            return new DataAccion().getAllByPerfil(e.getPerfil());
        } catch (SQLException ef){
            //int idCategoria, String msj, String urlDestino, int prioridad, String userResponsable
            new GrabarNotificacion(new Notificacion(5, "Excepcion ocurrida en "+this.getClass() +"."+this.getClass().getEnclosingMethod().getName(),
                    3,"ADMIN"));
            return null;
        }
    }

    public ArrayList<SistemaAccion> getAll(){
        try {
            return new DataAccion().getAll();
        } catch (SQLException ef){
            //int idCategoria, String msj, String urlDestino, int prioridad, String userResponsable
            new GrabarNotificacion(new Notificacion(5, "Excepcion ocurrida en "+this.getClass() +"."+this.getClass().getEnclosingMethod().getName(),
                    2,"ADMIN"));
            return null;
        }
    }

}
