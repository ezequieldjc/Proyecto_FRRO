package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DataSistemaModulo {

    public HashMap<Integer, String> getAllCodigos() throws SQLException {
        HashMap<Integer, String> configs = new HashMap<Integer, String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select id, codigo from sistema_modulo";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()){
            configs.put(rs.getInt("id"), rs.getString("codigo"));
        }
        return configs;
    }
}
