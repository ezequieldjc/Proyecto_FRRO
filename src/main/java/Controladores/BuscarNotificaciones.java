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
}
