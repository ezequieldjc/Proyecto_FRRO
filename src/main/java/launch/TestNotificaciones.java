package launch;

import Data.DataNotificacion;
import Entities.Notificaciones.Notificacion;
import Entities.Persona.PersonaEmpleado;

import java.sql.SQLException;

public class TestNotificaciones {

    public static void main(String[] args) {


        PersonaEmpleado e = new PersonaEmpleado(1);
        try {
            e.setNotificaciones(new DataNotificacion().getAllByUsuario(e));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for(Notificacion n : e.getNotificaciones()){

            System.out.println(n.getFechaCreacion());
        }


        Notificacion n = new Notificacion();



    }


}
