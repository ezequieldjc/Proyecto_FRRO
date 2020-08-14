package Controladores;

import Data.DataAccion;
import Data.DataSistemaModulo;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.System.SistemaAccion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ManejoAcciones {

    public ArrayList<SistemaAccion> getByFilter(SistemaAccion a){
        try {
            return new DataAccion().getByFilter(a);
        } catch (SQLException throwables) {
            new GrabarNotificacion(new Notificacion(7, "Excepcion ocurrida en "+this.getClass() +"."+this.getClass().getEnclosingMethod().getName(),
                    3,"ADMIN"));
            return null;
        }
    }

    public HashMap<Integer,String> getAllCodigos(){
        try {
            return new DataAccion().getAllCodigos();
        } catch (SQLException ef){
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(7));
            n.setMensaje("Excepcion ocurrida al intentar obtener los codigos de las acciones");
            n.setPrioridad(1);
            new GrabarNotificacion(n);
            return null;
        }
    }

}
