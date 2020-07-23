package Data;

import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;
import Entities.System.SistemaAccion;
import Entities.System.SistemaBoton;
import Entities.System.SistemaGlobalConfig;
import Entities.System.SistemaModulo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataAccion {

    public ArrayList<SistemaAccion> getAll() throws SQLException{
        ArrayList<SistemaAccion> acciones = new ArrayList<SistemaAccion>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select id, codigo, nombreAccion, `desc` from sistema_accion");
        rs = stmt.executeQuery();

        while (rs.next()){
                acciones.add(new SistemaAccion(rs.getInt("id"), rs.getString("codigo"), rs.getString("nombreAccion"), rs.getString("desc")));
        }
        stmt.close();
        rs.close();
        if (acciones.isEmpty())
            return null;

        return acciones;
    }

    public ArrayList<SistemaAccion> getAllByPerfil (PersonaPerfil p) throws SQLException{
        ArrayList<SistemaAccion> acciones = new ArrayList<SistemaAccion>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                        "select sa.id, sa.codigo, sa.nombreAccion, sa.`desc` " +
                        "from sistema_accion sa " +
                        "inner join sistema_perfil_accion spa " +
                        "on sa.id = spa.idAccion " +
                        "where spa.idPerfil = ?");
        stmt.setInt(1,p.getId());
        rs = stmt.executeQuery();
        while (rs.next()){
            acciones.add(new SistemaAccion(rs.getInt("sa.id"), rs.getString("sa.codigo"), rs.getString("sa.nombreAccion"), rs.getString("sa.desc")));
        }
        stmt.close();
        rs.close();
        return acciones;
    }

    public ArrayList<SistemaAccion> getAllByEmpleado (PersonaEmpleado e) throws SQLException{
        ArrayList<SistemaAccion> acciones = new ArrayList<SistemaAccion>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                "select sa.id, sa.codigo, sa.nombreAccion, sa.`desc` " +
                        "from sistema_accion sa " +
                        "inner join sistema_perfil_accion spa " +
                        "on sa.id = spa.idAccion " +
                        "where spa.idPerfil = ?");
        stmt.setInt(1, e.getPerfil().getId());
        rs = stmt.executeQuery();

        while (rs.next()){
            acciones.add(new SistemaAccion(rs.getInt("sa.id"), rs.getString("sa.codigo"), rs.getString("sa.nombreAccion"), rs.getString("sa.desc")));
        }
        stmt.close();
        rs.close();
        return acciones;
    }

    public boolean estaPermitido (PersonaEmpleado e , SistemaAccion a) throws  SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                "select count(*) loTiene " +
                        "from sistema_accion sa " +
                        "inner join sistema_perfil_accion spa " +
                        "on sa.id = spa.idAccion " +
                        "where spa.idPerfil = ? and sa.id = ?");
        stmt.setInt(1, e.getPerfil().getId());
        stmt.setInt(2, a.getId());
        rs = stmt.executeQuery();
        if (rs.next())
            return 1==rs.getInt("loTiene");
        stmt.close();
        rs.close();
        return false;
    }

    public boolean estaPermitidoByCodigoAccion (PersonaEmpleado e , SistemaAccion a) throws  SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                "select count(*) loTiene " +
                        "from sistema_accion sa " +
                        "inner join sistema_perfil_accion spa " +
                        "on sa.id = spa.idAccion " +
                        "where spa.idPerfil = ? and sa.codigo = ?");
        stmt.setInt(1, e.getPerfil().getId());
        stmt.setString(2, a.getCodigo());
        rs = stmt.executeQuery();
        if (rs.next())
            return 1==rs.getInt("loTiene");
        stmt.close();
        rs.close();
        return false;
    }

    public ArrayList<SistemaAccion> getByFilter(SistemaAccion a) throws SQLException {
        ArrayList<SistemaAccion> acciones = new ArrayList <SistemaAccion>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String where = "where id = id";
        if (!(a.getCodigo().equals(""))){
            where += " and codigo like '%"+a.getCodigo()+"%' ";
        }
        if (!(a.getDesc().equals(""))){
            where += " and `desc` like '%"+a.getDesc()+"%' ";
        }
        if(!(a.getNombre().equals(""))){
            where += " and nombreAccion like '%"+ a.getNombre()+"%' ";
        }
        String query = "select id,  codigo, nombreaccion, `desc` from sistema_accion " + where + ";";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()){
            //int id, String codigo, String nombre, String desc
            acciones.add(new SistemaAccion(rs.getInt("id"), rs.getString("codigo"), rs.getString("nombreAccion"), rs.getString("desc")));
        }
        stmt.close();
        rs.close();
        return acciones;
    }

    public HashMap<Integer, String> getAllCodigos() throws SQLException {
        HashMap<Integer, String> configs = new HashMap<Integer, String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select id, codigo from sistema_accion";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()){
            configs.put(rs.getInt("id"), rs.getString("codigo"));
        }
        stmt.close();
        rs.close();
        return configs;
    }
}
