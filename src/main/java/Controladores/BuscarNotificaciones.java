package Controladores;

import Data.DataNotificacion;
import Entities.Notificaciones.Notificacion;
import Entities.Persona.PersonaEmpleado;

import java.sql.SQLException;
import java.util.ArrayList;

public class BuscarNotificaciones {

    public ArrayList<Notificacion> getAllByUsuario(PersonaEmpleado e){
        try {
            return new DataNotificacion().getAllByUsuario(e);
        } catch (SQLException ef){
            ef.printStackTrace();
            return null;
        }
    }

    public ArrayList<Notificacion> getUnreadByUsuario(PersonaEmpleado e){
        try {
            return new DataNotificacion().getUnreadByUsuario(e);
        } catch (SQLException ef){
            ef.printStackTrace();
            return null;
        }
    }

    public Notificacion getOneByID(PersonaEmpleado e, Notificacion n ){
        try {
            return new DataNotificacion().getOneByID(e,n);
        } catch (SQLException throwables) {
            new GrabarNotificacion(new Notificacion(10, "Excepcion ocurrida en "+this.getClass() +"."+this.getClass().getEnclosingMethod().getName(),
                    2,e.getUsuario()));
            return null;
        }
    }

    public void setNotificacionAsRead(PersonaEmpleado e , Notificacion n){
        try {
            new DataNotificacion().setNotificacionAsRead(e,n);
        } catch (SQLException throwables) {
            new GrabarNotificacion(new Notificacion(10, "Excepcion ocurrida en "+this.getClass() +"."+this.getClass().getEnclosingMethod().getName(),
                    2,e.getUsuario()));
        }
    }
}
