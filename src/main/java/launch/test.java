package launch;

import Controladores.BuscarNombresAvatar;
import Controladores.BuscarSesiones;
import Controladores.Economy.Dolar_Operations;
import Controladores.HashearPassword;
import Controladores.JSON.Dolar_Handler;
import Controladores.VerificarLogin;
import Data.DataEconomyDolar;
import Data.DataGlobalConfig;
import Data.DataNotificacion;
import Data.DataPersonaEmpleado;
import Entities.Economy.DolarValue;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaSesiones;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class test {

    public static void main(String[] args) throws SQLException {

        ArrayList<DolarValue> valueList = new ArrayList<DolarValue>();
        float value = (float) 62.21;
        //marzo
        for (int x = 1; x<=161 ; x++){
            Timestamp t = new Timestamp(120,2,x,00,00,00,00);
            DolarValue d = new DolarValue(t, (float) (value*1.001));
            value*=1.001;
            valueList.add(d);
        }

        for (DolarValue d : valueList) {
            System.out.println(d.getFecha() + " : " + d.getValue());
        }

        DataEconomyDolar ded = new DataEconomyDolar();
        ded.insertAll(valueList);
    }

}
