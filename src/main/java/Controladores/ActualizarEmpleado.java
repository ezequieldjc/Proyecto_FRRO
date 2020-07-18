package Controladores;

import Data.DataPersonaEmpleado;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;

import java.sql.SQLException;

public class ActualizarEmpleado {

    public Object actualizarEmpleado (PersonaEmpleado pe , PersonaEmpleado e){
        try {
            new DataPersonaEmpleado().actualizar(pe);
        } catch (SQLException ef){
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(6));
            n.setMensaje("Excepcion ocurrida al intentar actualizar a : " + pe.getUsuario()+".");
            n.setResponsable(e);
            n.setPrioridad(3);
            new GrabarNotificacion(n);
            return n;
        }
        return true;
    }

}
