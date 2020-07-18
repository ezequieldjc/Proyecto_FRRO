package Data;

import Entities.Persona.PersonaDocumento;
import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;
import Entities.System.SistemaGlobalConfig;
import Entities.System.SistemaModulo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class DataGlobalConfig {

    public ArrayList<SistemaGlobalConfig> getAll() throws SQLException {
        ArrayList<SistemaGlobalConfig> configs = new ArrayList<SistemaGlobalConfig>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select gc.id, gc.idModulo, gc.nombreParametro, gc.codigo, " +
                "gc.numeroParametro, gc.nombreAtributo, gc.valorAtributo, m.id, m.codigo, m.nombre, m.desc " +
                "from sistema_global_config gc " +
                "inner join sistema_modulo m " +
                "on gc.idModulo = m.id ");
        rs = stmt.executeQuery();
        while (rs.next()){
            SistemaGlobalConfig gc = new SistemaGlobalConfig(rs.getInt("gc.id"),rs.getString("gc.codigo"),rs.getString("gc.nombreParametro"),
                    rs.getInt("gc.numeroParametro"), rs.getString("gc.nombreAtributo"), rs.getString("gc.valorAtributo"),
                        new SistemaModulo(rs.getInt("m.id"), rs.getString("m.codigo"), rs.getString("m.nombre"), rs.getString("m.desc")));
            configs.add(gc);
        }
        if (configs.isEmpty())
            return null;
        return configs;
    }

    public SistemaGlobalConfig getOneByID(SistemaGlobalConfig g) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select gc.id, gc.idModulo, gc.nombreParametro, gc.codigo,  " +
                "gc.numeroParametro, gc.nombreAtributo, gc.valorAtributo, m.id, m.codigo, m.nombre, m.desc " +
                "from sistema_global_config gc " +
                "inner join sistema_modulo m " +
                "on gc.idModulo = m.id " +
                "where gc.id = ? ");
        stmt.setInt(1,g.getId());
        rs = stmt.executeQuery();
        if (rs.next())
            //int id, String codigo, String nombreParametro, int numeroParametro, String nombreAtributo, String valorAtributo, SistemaModulo modulo
            return new SistemaGlobalConfig(rs.getInt("gc.id"),rs.getString("gc.codigo"),rs.getString("gc.nombreParametro"),
                    rs.getInt("gc.numeroParametro"), rs.getString("gc.nombreAtributo"), rs.getString("gc.valorAtributo"),
                    new SistemaModulo(rs.getInt("m.id"), rs.getString("m.codigo"), rs.getString("m.nombre"), rs.getString("m.desc")));
        else
            return null;
    }

    public SistemaGlobalConfig getOneByCodigo(SistemaGlobalConfig g) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select gc.id, gc.idModulo, gc.nombreParametro, gc.codigo, " +
                "gc.numeroParametro, gc.nombreAtributo, gc.valorAtributo, m.id, m.codigo, m.nombre, m.desc " +
                "from sistema_global_config gc " +
                "inner join sistema_modulo m " +
                "on gc.idModulo = m.id " +
                "where gc.codigo = ? ");
        stmt.setString(1,g.getCodigo());
        rs = stmt.executeQuery();
        if (rs.next())
            //int id, String codigo, String nombreParametro, int numeroParametro, String nombreAtributo, String valorAtributo, SistemaModulo modulo
            return new SistemaGlobalConfig(rs.getInt("gc.id"),rs.getString("gc.codigo"),rs.getString("gc.nombreParametro"),
                    rs.getInt("gc.numeroParametro"), rs.getString("gc.nombreAtributo"), rs.getString("gc.valorAtributo"),
                    new SistemaModulo(rs.getInt("m.id"), rs.getString("m.codigo"), rs.getString("m.nombre"), rs.getString("m.desc")));
        else
            return null;
    }

    public ArrayList<SistemaGlobalConfig> getByFilter(SistemaGlobalConfig g) throws SQLException {
        ArrayList<SistemaGlobalConfig> configs = new ArrayList<SistemaGlobalConfig>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String where = "where gc.id = gc.id";
        if (!(g.getModulo().getCodigo().equals(""))){
            where += " and m.codigo like '%"+g.getModulo().getCodigo()+"%' ";
        }
        if (!(g.getCodigo().equals(""))){
            where += " and gc.codigo like '%"+g.getCodigo()+"%' ";
        }
        if(!(g.getNombreParametro().equals(""))){
            where += " and gc.nombreParametro like '%"+ g.getNombreParametro()+"%' ";
        }
        if(!(g.getValorAtributo().equals(""))){
            where += " and gc.valorAtributo like '" + g.getValorAtributo() +"'";
        }
        String query = "select gc.id, gc.idModulo, gc.nombreParametro, gc.codigo, " +
                "gc.numeroParametro, gc.nombreAtributo, gc.valorAtributo, m.id, m.codigo, m.nombre, m.desc " +
                "from sistema_global_config gc " +
                "inner join sistema_modulo m " +
                "on gc.idModulo = m.id " + where + ";";

        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()){
            SistemaGlobalConfig gc = new SistemaGlobalConfig(rs.getInt("gc.id"),rs.getString("gc.codigo"),rs.getString("gc.nombreParametro"),
                    rs.getInt("gc.numeroParametro"), rs.getString("gc.nombreAtributo"), rs.getString("gc.valorAtributo"),
                    new SistemaModulo(rs.getInt("m.id"), rs.getString("m.codigo"), rs.getString("m.nombre"), rs.getString("m.desc")));
            configs.add(gc);
        }
        return configs;
    }


}
