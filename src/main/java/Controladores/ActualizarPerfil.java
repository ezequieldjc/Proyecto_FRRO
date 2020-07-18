package Controladores;

import Data.DataPerfil;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;

import java.sql.SQLException;

public class ActualizarPerfil {

    public Object actualizarPerfil (PersonaEmpleado e , PersonaPerfil p){
        try {
            new DataPerfil().actualizar(p);
        } catch (SQLException ef){
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(6));
            n.setMensaje("Excepcion ocurrida al intentar actualizar el perfil : " + p.getCodigo()+".");
            n.setResponsable(e);
            n.setPrioridad(3);
            new GrabarNotificacion(n);
            return n;
        }
        return true;
    }

}
