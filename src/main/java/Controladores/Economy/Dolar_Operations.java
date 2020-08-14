package Controladores.Economy;

import Controladores.GrabarNotificacion;
import Data.DataEconomyDolar;
import Entities.Economy.DolarValue;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dolar_Operations {

    private String url = "https://api.estadisticasbcra.com/usd_of";
    private String token = "BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2Mjc0MjMyNDQsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJlemVxdWllbC5kamNAZ21haWwuY29tIn0.54RtmT6EF8BwQ212VKN1hv0dYEf5jvt1osDbN_cN4IJOdikwVaefmrgtPUItQvUiCk9m-aX9is__Z0p7SvzFXA";

    public String getDolarValueFromBCRA(){
        HttpsURLConnection con = null;
        try {
            URL u = new URL(url);
            con = (HttpsURLConnection) u.openConnection();
            //con.setRequestProperty("d","2008-01-01");
            con.setRequestProperty("Authorization",token);
            //con.setRequestProperty("d","\"2019-12-13\"");
            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            //System.out.println(sb.toString());
            return sb.toString();
            /*Gson g = new Gson();
            DolarValue dv = g.fromJson(sb.toString(),DolarValue.class);
            String rta = g.toJson(dv);*/
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }



    }

    public void insertDolarValue(ArrayList<DolarValue> dv){
        try {
            new DataEconomyDolar().insertAll(dv);
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(9));
            n.setMensaje("Excepcion ocurrida al intentar actualizar economy_dolar_history");
            n.setPrioridad(2);
            n.setResponsable(null);
            new GrabarNotificacion(n);
        }
    }

    public ArrayList<DolarValue> getAll(){

        try {
            return new DataEconomyDolar().getAll();
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(9));
            n.setMensaje("Excepcion ocurrida al intentar actualizar economy_dolar_history");
            n.setPrioridad(2);
            n.setResponsable(null);
            new GrabarNotificacion(n);
            return null;
        }
    }

    public ArrayList<DolarValue> getLast500(){

        try {
            return new DataEconomyDolar().getLast500();
        } catch (SQLException throwables) {
            Notificacion n = new Notificacion();
            n.setCategoria(new NotificacionCategoria(9));
            n.setMensaje("Excepcion ocurrida al intentar actualizar economy_dolar_history");
            n.setPrioridad(2);
            n.setResponsable(null);
            new GrabarNotificacion(n);
            return null;
        }
    }

}
