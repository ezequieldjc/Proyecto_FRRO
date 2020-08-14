package Data;

import Controladores.HashearPassword;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.Persona.PersonaEmpleado;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class DataNotificacion {

    /*
    Estos metodos consultan la vista que no se puede replicar en google cloud
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

    */

    //Este metodo no deberia utilizarse si se pudiese utilizar la vista 'notificaciones'
    public ArrayList<Notificacion> getAllByUsuario(PersonaEmpleado e) throws SQLException {
        ArrayList<Notificacion> notis = new ArrayList<Notificacion>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query =
                "select `sn`.`id` AS `id`, " +
                        "`p`.`id` AS `idEmpleado`, " +
                        "`p`.`usuario` AS `usuarioEmpleado`, " +
                        "`snc`.`id` AS `idCategoria`, " +
                        "`snc`.`codigo` AS `codigoCategoria`, " +
                        "`snc`.`nombre` AS `nombre`, " +
                        "`sn`.`texto` AS `texto`, " +
                        "`sn`.`prioridad` AS `prioridad`, " +
                        "`sn`.`link` AS `link`, " +
                        "`snp`.`estado` AS `estado`, " +
                        "`sn`.`fechaCreacion` AS `fechaCreacion`, " +
                        "`pee`.`id` AS `idResponsable`, " +
                        "`pee`.`usuario` AS `usuarioResponsable`, " +
                        "`pee`.`img` AS `img` " +
                        "from persona_empleado p " +
                        "inner join persona_perfil pp " +
                        "on p.idPerfil = pp.id " +
                        "left join sistema_personaPerfil_notificacionCategoria spn " +
                        "on spn.idPersonaPerfil = pp.id " +
                        "left join sistema_notificacion_categoria snc " +
                        "on snc.id = spn.idNotificacionCategoria " +
                        "inner join sistema_notificacion sn " +
                        "on sn.idCategoria = snc.id " +
                        "left join sistema_notificacion_persona snp " +
                        "on snp.idNotificacion = sn.id and snp.idEmpleado=p.id " +
                        "left join persona_empleado pee " +
                        "on sn.idEmpleado = pee.id " +
                        "where p.id = ? " +
                        "order by `sn`.`id`;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        stmt.setInt(1,e.getId());
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

    public Notificacion getOneByID(PersonaEmpleado e, Notificacion n) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query =
                "select `sn`.`id` AS `id`, " +
                        "`p`.`id` AS `idEmpleado`, " +
                        "`p`.`usuario` AS `usuarioEmpleado`, " +
                        "`snc`.`id` AS `idCategoria`, " +
                        "`snc`.`codigo` AS `codigoCategoria`, " +
                        "`snc`.`nombre` AS `nombre`, " +
                        "`sn`.`texto` AS `texto`, " +
                        "`sn`.`prioridad` AS `prioridad`, " +
                        "`sn`.`link` AS `link`, " +
                        "`snp`.`estado` AS `estado`, " +
                        "`sn`.`fechaCreacion` AS `fechaCreacion`, " +
                        "`pee`.`id` AS `idResponsable`, " +
                        "`pee`.`usuario` AS `usuarioResponsable`, " +
                        "`pee`.`img` AS `img` " +
                        "from persona_empleado p " +
                        "inner join persona_perfil pp " +
                        "on p.idPerfil = pp.id " +
                        "left join sistema_personaPerfil_notificacionCategoria spn " +
                        "on spn.idPersonaPerfil = pp.id " +
                        "left join sistema_notificacion_categoria snc " +
                        "on snc.id = spn.idNotificacionCategoria " +
                        "inner join sistema_notificacion sn " +
                        "on sn.idCategoria = snc.id " +
                        "left join sistema_notificacion_persona snp " +
                        "on snp.idNotificacion = sn.id and snp.idEmpleado=p.id " +
                        "left join persona_empleado pee " +
                        "on sn.idEmpleado = pee.id " +
                        "where p.id = ? and sn.id = ? " +
                        "order by `sn`.`id`;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        stmt.setInt(1,e.getId());
        stmt.setInt(2,n.getId());
        rs = stmt.executeQuery();
        if (rs.next()){
            //int id, NotificacionCategoria categoria, String mensaje, String urlDestino, int prioridad
            PersonaEmpleado em = new PersonaEmpleado(rs.getInt("idResponsable"), rs.getString("usuarioResponsable"));
            em.setImg(rs.getString("img"));
            return new Notificacion(rs.getInt("id"), new NotificacionCategoria(rs.getInt("idCategoria"),
                    rs.getString("codigoCategoria")), rs.getString("texto"), rs.getString("link"), rs.getInt("prioridad"),
                    em, rs.getInt("estado"), rs.getTimestamp("fechaCreacion"));
        }
        return null;
    }

    //Este metodo no deberia utilizarse si se pudiese utilizar la vista 'notificaciones'
    public ArrayList<Notificacion> getUnreadByUsuario(PersonaEmpleado e) throws SQLException {
        ArrayList<Notificacion> notis = new ArrayList<Notificacion>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query =
                "select `sn`.`id` AS `id`, " +
                        "`p`.`id` AS `idEmpleado`, " +
                        "`p`.`usuario` AS `usuarioEmpleado`, " +
                        "`snc`.`id` AS `idCategoria`, " +
                        "`snc`.`codigo` AS `codigoCategoria`, " +
                        "`snc`.`nombre` AS `nombre`, " +
                        "`sn`.`texto` AS `texto`, " +
                        "`sn`.`prioridad` AS `prioridad`, " +
                        "`sn`.`link` AS `link`, " +
                        "`snp`.`estado` AS `estado`, " +
                        "`sn`.`fechaCreacion` AS `fechaCreacion`, " +
                        "`pee`.`id` AS `idResponsable`, " +
                        "`pee`.`usuario` AS `usuarioResponsable`, " +
                        "`pee`.`img` AS `img` " +
                        "from persona_empleado p " +
                        "inner join persona_perfil pp " +
                        "on p.idPerfil = pp.id " +
                        "left join sistema_personaPerfil_notificacionCategoria spn " +
                        "on spn.idPersonaPerfil = pp.id " +
                        "left join sistema_notificacion_categoria snc " +
                        "on snc.id = spn.idNotificacionCategoria " +
                        "inner join sistema_notificacion sn " +
                        "on sn.idCategoria = snc.id " +
                        "left join sistema_notificacion_persona snp " +
                        "on snp.idNotificacion = sn.id and snp.idEmpleado=p.id " +
                        "left join persona_empleado pee " +
                        "on sn.idEmpleado = pee.id " +
                        "where p.id = ? and snp.estado = 0;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        stmt.setInt(1,e.getId());
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

    public void setNotificacionAsRead (PersonaEmpleado personaEmpleado ,Notificacion notificacion) throws SQLException{
        PreparedStatement stmt = null;

        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                "update sistema_notificacion_persona set estado = 1 where idNotificacion = ? and idEmpleado = ?; ");
        stmt.setInt(1, notificacion.getId());
        stmt.setInt(2, personaEmpleado.getId());

        stmt.executeUpdate();

    }


    /*
    Este es el metodo que deberia utilizarse si el trigger y la vista funcionaran en google cloud
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

    }*/

    public void registrarNotificacion (Notificacion n) throws SQLException{
        PreparedStatement stmt = null;

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
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs != null && rs.next()) {
            int key = rs.getInt( 1);
            n.setId(key);
        }
        this.registrarNotificacionEnSNP(n);
    }

    private void registrarNotificacionEnSNP (Notificacion n) throws SQLException{
        //este metodo hace el trabajo del trigger
        PreparedStatement stmt = null;
        int rs;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                    "insert into sistema_notificacion_persona (idEmpleado, idNotificacion, estado) " +
                            "select pe.id, sn.id, 0 " +
                            "from sistema_notificacion sn " +
                            "inner join sistema_notificacion_categoria snc " +
                            "on snc.id = sn.idCategoria " +
                            "left join sistema_personaPerfil_notificacionCategoria spn " +
                            "on snc.id = spn.idNotificacionCategoria " +
                            "left join persona_perfil spp " +
                            "on spp.id = spn.idPersonaPerfil " +
                            "left join persona_empleado pe " +
                            " on spp.id = pe.idPerfil " +
                            "where sn.id = ?;"
        );
        stmt.setInt(1,n.getId());
        stmt.executeUpdate();
    }
}
