package Controladores;

import Data.DataBoton;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaBoton;

import java.sql.SQLException;
import java.util.ArrayList;

public class BuscarBotones {
    public ArrayList<SistemaBoton> buscarBotones(PersonaEmpleado e){
        try {
            return new DataBoton().getAllByEmpleado(e);
        } catch (SQLException ef){
            //int idCategoria, String msj, String urlDestino, int prioridad, String userResponsable
            new GrabarNotificacion(new Notificacion(8, "Excepcion ocurrida en "+this.getClass() +"."+this.getClass().getEnclosingMethod().getName(),
                    3,"ADMIN"));
            return null;
        }
    }

    public ArrayList<SistemaBoton> getAll(){
        try {
            return new DataBoton().getAll();
        } catch (SQLException ef){
            //int idCategoria, String msj, String urlDestino, int prioridad, String userResponsable
            new GrabarNotificacion(new Notificacion(8, "Excepcion ocurrida en "+this.getClass() +"."+this.getClass().getEnclosingMethod().getName(),
                    2,"ADMIN"));
            return null;
        }
    }


}
