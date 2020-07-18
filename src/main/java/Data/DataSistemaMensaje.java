package Data;

import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaGlobalConfig;
import Entities.System.SistemaMensaje;
import Entities.System.SistemaModulo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataSistemaMensaje {

    public DataSistemaMensaje (){

    }

    public DataSistemaMensaje (SistemaMensaje m) throws SQLException{
        this.grabarMensaje(m);
    }

    public ArrayList<SistemaMensaje> getAll(){
        ArrayList<SistemaMensaje> mensajes = new ArrayList<SistemaMensaje>();

        return mensajes;
    }

    private void grabarMensaje (SistemaMensaje m) throws SQLException {
        PreparedStatement stmt = null;
        if (m.getEmpleado()!=null) {
            stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                    "insert into sistema_mensaje (idModulo, mensaje, idUsuario) values (?, ?, ?);");
            stmt.setInt(1, m.getSistemaModulo().getId());
            stmt.setString(2, m.getMensaje());
            stmt.setInt(3, m.getEmpleado().getId());
        } else {
            stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                    "insert into sistema_mensaje (idModulo, mensaje) values (?, ?);");
            stmt.setInt(1, m.getSistemaModulo().getId());
            stmt.setString(2, m.getMensaje());
        }
        stmt.executeUpdate();
    }

    public ArrayList<SistemaMensaje> getByFilter(SistemaMensaje m) throws SQLException {
        ArrayList<SistemaMensaje> mensajes = new ArrayList<SistemaMensaje>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String where = "where sm.id = sm.id";
        if (!(m.getSistemaModulo().getCodigo().equals(""))){
            where += " and s.codigo like '%"+m.getSistemaModulo().getCodigo()+"%' ";
        }
        if (!(m.getEmpleado().getUsuario().equals(""))){
            where += " and pe.usuario like '%"+m.getEmpleado().getUsuario()+"%' ";
        }
        if(!(m.getMensaje().equals(""))){
            where += " and sm.mensaje like '%"+ m.getMensaje()+"%' ";
        }

        String query = "select sm.id, sm.mensaje, sm.fecha, s.id, s.codigo, s.nombre, s.`desc`, pe.usuario, pe.id from sistema_mensaje sm " +
                "left join sistema_modulo s on sm.idModulo = s.id " +
                "left join persona_empleado pe on sm.idUsuario = pe.id " + where + ";";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()){
            //int id, String codigo, SistemaModulo sistemaModulo, String mensaje, Timestamp tiempo, PersonaEmpleado empleado
            SistemaModulo s = new SistemaModulo(rs.getInt("s.id"));
            s.setCodigo(rs.getString("s.codigo"));
            mensajes.add(new SistemaMensaje (rs.getInt("sm.id"), s,
                    rs.getString("sm.mensaje"), rs.getTimestamp("sm.fecha"), new PersonaEmpleado(rs.getInt("pe.id"), rs.getString("pe.usuario"))));
        }
        return mensajes;
    }



}
