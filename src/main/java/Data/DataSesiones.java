package Data;

import Controladores.BuscarEmpleado;
import Controladores.HashearPassword;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaGlobalConfig;
import Entities.System.SistemaLogLogins;
import Entities.System.SistemaModulo;
import Entities.System.SistemaSesiones;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataSesiones {

    public ArrayList<SistemaSesiones> getAll() throws SQLException {
        ArrayList<SistemaSesiones> sesiones = new ArrayList<SistemaSesiones>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select id, idEmpleado," +
                " fechaDesde, fechaHasta, estaAbierta from sistema_sesion");
        rs = stmt.executeQuery();
        while (rs.next()){
            //int id, PersonaEmpleado empleado, Timestamp fechaDesde, Timestamp fechaHasta
            sesiones.add(new SistemaSesiones(rs.getInt("id"), new DataPersonaEmpleado().getByID(new PersonaEmpleado(rs.getInt("idEmpleado"))),
                    rs.getTimestamp("fechaDesde"), rs.getTimestamp("fechaHasta")));
        }
        if (sesiones.isEmpty())
            return null;
        return  sesiones;
    }

    public void registrarNuevaSesion (SistemaSesiones s) {
        PreparedStatement stmt = null;
        int rs;
        try {
            stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("insert into sistema_sesion (idEmpleado) " +
                    "values (?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, s.getEmpleado().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrarUltimaSesion (PersonaEmpleado e ) throws SQLException {
        PreparedStatement stmt = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                "update sistema_sesion " +
                        "set fechaHasta = current_timestamp " +
                        "where idEmpleado = ? and id = (select max(id) from sistema_sesion where idEmpleado=?) ");
        stmt.setInt(1, e.getId());
        stmt.setInt(2, e.getId());
        stmt.executeUpdate();

    }

    public void registrarNuevaSesion (PersonaEmpleado e) {
        PreparedStatement stmt = null;
        int rs;
        try {
            stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("insert into sistema_sesion (idEmpleado) " +
                    "values (?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, e.getId());
            stmt.executeUpdate();

        } catch (SQLException f) {
            f.printStackTrace();
        }
    }

    public Boolean tieneSesionAbierta (PersonaEmpleado e) throws SQLException {
        if (e!=null) {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select count(id) cantSesionesAbiertas from sistema_sesion where fechaHasta is null and idEmpleado = ?");
            stmt.setInt(1,e.getId());
            rs = stmt.executeQuery();
            if (rs.next())
                return !(rs.getInt("cantSesionesAbiertas")==0);
        }
        return false;
    }

    public int cantSesionesAbiertas (PersonaEmpleado e) throws SQLException {
        if (e!=null) {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select count(id) cantSesionesAbiertas from sistema_sesion where fechaHasta is null and idEmpleado = ?");
            stmt.setInt(1,e.getId());
            rs = stmt.executeQuery();
            if (rs.next())
                return rs.getInt("cantSesionesAbiertas");
        }
        return -1;
    }

    public void cerrarSesionesPorEmpleado (PersonaEmpleado e) throws SQLException{
        PreparedStatement stmt = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                    "update sistema_sesion " +
                        "set fechaHasta = current_timestamp " +
                        "where idEmpleado = ? and fechaHasta is null ");
        stmt.setInt(1, e.getId());
        stmt.executeUpdate();

    }

    public ArrayList<SistemaSesiones> getSesiones() throws SQLException{
        //Ultima sesion por usuario
        ArrayList<SistemaSesiones> sesiones = new ArrayList<SistemaSesiones>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                "select ss.id, ss.fechaDesde, ss.fechaHasta, pe.id, pe.usuario, pe.nombre, pe.apellido " +
                        "from persona_empleado pe " +
                        "left join sistema_sesion ss " +
                        "on ss.idEmpleado = pe.id " +
                        "where fechaDesde in ( " +
                        "SELECT max(fechaDesde) " +
                        "FROM sistema_sesion " +
                        "group by idEmpleado) ;");
        rs = stmt.executeQuery();
        while (rs.next()){
            //int id, PersonaEmpleado empleado, Timestamp fechaDesde, Timestamp fechaHasta
            PersonaEmpleado p =  new PersonaEmpleado( rs.getInt("pe.id"));

            sesiones.add(new SistemaSesiones(rs.getInt("ss.id"),
                    new PersonaEmpleado(rs.getInt("pe.id"), rs.getString("pe.usuario"), rs.getString ("pe.nombre"), rs.getString("pe.apellido")),
                    rs.getTimestamp("ss.fechaDesde"), rs.getTimestamp("ss.fechaHasta")));
        }
        if (!(sesiones.isEmpty()))
                return sesiones;
        return null;
    }

    public ArrayList<SistemaSesiones> getSesionesByFilter(String id, String nya, String estado) throws SQLException{
        ArrayList<SistemaSesiones> sesiones = new ArrayList<SistemaSesiones>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String clausula = "";
        if (!id.equals(""))
            clausula = clausula + " and pe.id = " + id ;
        if (!nya.equals(""))
            clausula = clausula + " and (concat(pe.nombre, ' ', pe.apellido) like "+"'%"+nya+"%' or concat(pe.nombre, ' ', pe.apellido) like "+"'%"+nya+"%' )"  ;
        if(estado.equals("2"))
            clausula = clausula + " and ss.fechaHasta is not null " ;
        else if (estado.equals("1"))
            clausula = clausula + " and ss.fechaHasta is null " ;
        String query = "select ss.id, ss.fechaDesde, ss.fechaHasta, pe.id, pe.usuario, pe.nombre, pe.apellido " +
                "from persona_empleado pe " +
                "left join sistema_sesion ss " +
                "on ss.idEmpleado = pe.id " +
                "where fechaDesde in ( " +
                "SELECT max(fechaDesde) " +
                "FROM sistema_sesion " +
                "group by idEmpleado) " + clausula + ";";
        rs = DataConnectioniMac.getInstancia().getConn().prepareStatement(query).executeQuery();
        while (rs.next()){
            PersonaEmpleado p =  new PersonaEmpleado( rs.getInt("pe.id"));
            sesiones.add(new SistemaSesiones(rs.getInt("ss.id"),
                    new PersonaEmpleado(rs.getInt("pe.id"), rs.getString("pe.usuario"), rs.getString ("pe.nombre"), rs.getString("pe.apellido")),
                    rs.getTimestamp("ss.fechaDesde"), rs.getTimestamp("ss.fechaHasta")));
        }
        return sesiones;
    }

}
