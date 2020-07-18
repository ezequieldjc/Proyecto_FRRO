package Data;

import Controladores.HashearPassword;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.Persona.PersonaEmpleado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataNotificacion {

    public ArrayList<Notificacion> getAllByUsuario(PersonaEmpleado e) throws SQLException {
        ArrayList<Notificacion> notis = new ArrayList<Notificacion>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query =
                "SELECT id, idCategoria, idEmpleado, usuarioEmpleado,codigoCategoria, codigoCategoria, texto, link, " +
                        "idResponsable, usuarioResponsable, prioridad, estado, fechaCreacion, img " +
                "FROM notificaciones " +
                        "where idEmpleado = " + e.getId() + " and estado is not null" +
                        " order by prioridad desc;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()){
            //int id, NotificacionCategoria categoria, String mensaje, String urlDestino, int prioridad
            PersonaEmpleado em = new PersonaEmpleado(rs.getInt("idResponsable"), rs.getString("usuarioResponsable"));
            em.setImg(rs.getString("img"));
            notis.add(new Notificacion(rs.getInt("id"), new NotificacionCategoria(rs.getInt("idCategoria"),
                    rs.getString("codigoCategoria")), rs.getString("texto"), rs.getString("link"), rs.getInt("prioridad"),
                    em, rs.getInt("estado"), rs.getTimestamp("fechaCreacion")));
        }
        return notis;
    }

    public ArrayList<Notificacion> getUnreadByUsuario(PersonaEmpleado e) throws SQLException {
        ArrayList<Notificacion> notis = new ArrayList<Notificacion>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query =
                "SELECT id, idCategoria, idEmpleado, usuarioEmpleado ,codigoCategoria, codigoCategoria, texto, link, " +
                        " idResponsable, usuarioResponsable, prioridad, estado, fechaCreacion, img " +
                        " FROM notificaciones " +
                        " where idEmpleado = " + e.getId() + " and estado in (0,1)" +
                        " order by prioridad desc;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()){
            //int id, NotificacionCategoria categoria, String mensaje, String urlDestino, int prioridad
            PersonaEmpleado em = new PersonaEmpleado(rs.getInt("idResponsable"), rs.getString("usuarioResponsable"));
            em.setImg(rs.getString("img"));
            notis.add(new Notificacion(rs.getInt("id"), new NotificacionCategoria(rs.getInt("idCategoria"),
                    rs.getString("codigoCategoria")), rs.getString("texto"), rs.getString("link"), rs.getInt("prioridad"),
                    em,
                    rs.getInt("estado"), rs.getTimestamp("fechaCreacion")));
        }
        return notis;
    }

    public void registrarNotificacion (Notificacion n) throws SQLException{
        PreparedStatement stmt = null;
        int rs;

        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                "insert into sistema_notificacion (idCategoria, idEmpleado, prioridad, texto, link ) values (?,?,?,?,?) ",
                Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, n.getCategoria().getId());
        stmt.setInt(3, n.getPrioridad());
        stmt.setString(4, n.getMensaje());
        stmt.setString(5,n.getUrlDestino());
        if (n.getResponsable() ==null){
            stmt.setInt(2,1);
        } else {
            //stmt.setInt(2, n.getResponsable().getId());
            stmt.setInt(2,1);
        }
        stmt.executeUpdate();

    }
}
