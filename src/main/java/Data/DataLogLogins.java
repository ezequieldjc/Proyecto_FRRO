package Data;

import Controladores.HashearPassword;
import Entities.System.SistemaLogLogins;
import Entities.System.SistemaGlobalConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataLogLogins {

    public ArrayList<SistemaLogLogins> getAll() throws SQLException {
        ArrayList<SistemaLogLogins> logueos = new ArrayList<SistemaLogLogins>();

            ArrayList<SistemaGlobalConfig> configs = new ArrayList<SistemaGlobalConfig>();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                    "select id, user, password, logintime, result " +
                    " from sistema_log_logins");
            rs = stmt.executeQuery();
            while (rs.next()){
                logueos.add( new SistemaLogLogins(rs.getInt("id"), rs.getString("user"), rs.getString("password"), rs.getTimestamp("logintime"), rs.getBoolean("result")));
            }
            return logueos;
    }

    public void registrarLogin (SistemaLogLogins l) {
        PreparedStatement stmt = null;
        int rs;
        try {
            stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("insert into sistema_log_logins (user, password, result) values (?,?,?) ");
            stmt.setString(1, l.getUsuario());
            stmt.setString(2, new HashearPassword().hidePwd(l.getPassword()));
            stmt.setBoolean(3, l.getResultado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SistemaLogLogins> getByFilter (SistemaLogLogins l) throws SQLException{
        ArrayList<SistemaLogLogins> logins = new ArrayList<SistemaLogLogins>();
        String where = "where id = id";
        if(l.getUsuario() != null && !(l.getUsuario().equals(""))){
            where += " and user like '%"+l.getUsuario()+"%'";
        }
        if(l.getResultado() != null) {
            if (l.getResultado())
                where += " and result";
            else
                where += " and not result";
        }
        String query = "select id, user, password, logintime, result " +
                "from sistema_log_logins "
                + where;
        PreparedStatement stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        ResultSet rs =stmt.executeQuery();
        while (rs.next())
            logins.add(new SistemaLogLogins(rs.getInt("id"), rs.getString("user"), rs.getString("password"), rs.getTimestamp("loginTime"), rs.getBoolean("result")));
        return logins;
    }



}
