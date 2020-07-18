package Controladores;

import Data.DataNotificacion;
import Entities.Notificaciones.Notificacion;

import java.sql.SQLException;

public class GrabarNotificacion {

    public GrabarNotificacion(Notificacion n){
        try {
            new DataNotificacion().registrarNotificacion(n);
        } catch (SQLException ef){
            ef.printStackTrace();
        }
    }
}
