package Data;

import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;
import Entities.System.SistemaAccion;
import Entities.System.SistemaBoton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBoton {

    public ArrayList<SistemaBoton> getAll() throws SQLException{
        ArrayList<SistemaBoton> botones = new ArrayList<SistemaBoton>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("" +
                "select id, codigo, nombre, idFather, valor, habilitado, icon, url, isCollapse " +
                "from sistema_boton " +
                "order by habilitado desc , cast(valor as unsigned)");
        rs = stmt.executeQuery();

        while (rs.next()){
            //int id, String codigo, String nombre, int idPadre, float value, boolean enabled, String icon, String url, boolean isCollapse
            botones.add(new SistemaBoton(rs.getInt("id"), rs.getString("codigo"), rs.getString("nombre"), rs.getInt("idFather"), rs.getFloat("valor"),
                    rs.getBoolean("habilitado"), rs.getString("icon"), rs.getString("url"), rs.getBoolean("isCollapse")));
        }
        return botones;
    }

    public ArrayList<SistemaBoton> getAllByPerfil (PersonaPerfil p) throws SQLException{
        ArrayList<SistemaBoton> botones = new ArrayList<SistemaBoton>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                        "select sb.id, sb.codigo, sb.nombre, sb.idFather, sb.valor, sb.habilitado, sb.icon, sb.url, sb.isCollapse " +
                                "from sistema_boton sb " +
                                "inner join sistema_perfil_boton spa " +
                                "on sb.id = spa.idBoton " +
                                "where spa.idPerfil = ? " +
                                "order by habilitado desc , cast(valor as unsigned)");
        stmt.setInt(1,p.getId());
        rs = stmt.executeQuery();
        while (rs.next()){
            botones.add(new SistemaBoton(rs.getInt("id"), rs.getString("codigo"), rs.getString("nombre"), rs.getInt("idFather"), rs.getFloat("valor"),
                    rs.getBoolean("habilitado"), rs.getString("icon"), rs.getString("url"), rs.getBoolean("isCollapse")));
        }
        return botones;
    }

    public ArrayList<SistemaBoton> getAllByEmpleado (PersonaEmpleado e) throws SQLException{
        ArrayList<SistemaBoton> botones = new ArrayList<SistemaBoton>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                "select sb.id, codigo, nombre, idFather, valor, habilitado, icon, url, isCollapse " +
                        "from sistema_boton sb " +
                        "inner join sistema_perfil_boton spb " +
                        "on sb.id = spb.idBoton " +
                        "where spb.idPerfil = ? " +
                        "order by  cast(valor as unsigned) ;");
        stmt.setInt(1, e.getPerfil().getId());
        rs = stmt.executeQuery();

        while (rs.next()){
            botones.add(new SistemaBoton(rs.getInt("id"), rs.getString("codigo"), rs.getString("nombre"), rs.getInt("idFather"), rs.getFloat("valor"),
                    rs.getBoolean("habilitado"), rs.getString("icon"), rs.getString("url"), rs.getBoolean("isCollapse")));
        }
        if (botones.isEmpty())
            return null;
        return botones;
    }

    public boolean estaPermitido (PersonaEmpleado e , SistemaBoton b) throws  SQLException{

        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                "select count(*) loTiene " +
                        "from sistema_boton sb " +
                        "inner join sistema_perfil_boton spb " +
                        "on sb.id = spb.idBoton " +
                        "where spb.idPerfil = ? and sb.url = ?");
        stmt.setInt(1, e.getPerfil().getId());
        stmt.setString(2, b.getUrl());
        rs = stmt.executeQuery();
        if (rs.next())
            return !(0==rs.getInt("loTiene"));
        return false;
    }
}
