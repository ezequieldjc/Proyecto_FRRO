package Data;

import Controladores.HashearPassword;
import Entities.Economy.DolarValue;
import Entities.System.SistemaGlobalConfig;
import Entities.System.SistemaModulo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DataEconomyDolar {

    public void insertAll(ArrayList<DolarValue> valueArrayList) throws SQLException{
        PreparedStatement stmt = null;

        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("insert into economy_dolar_history (date,value) values (?,?) ");
        for (DolarValue dv : valueArrayList){
            stmt.setTimestamp(1,dv.getFecha());
            stmt.setFloat(2,dv.getValue());
            stmt.executeUpdate();
        }
    }

    public ArrayList<DolarValue> getAll() throws SQLException{
        ArrayList<DolarValue> valores = new ArrayList<DolarValue>();
        PreparedStatement stmt = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select date, value from economy_dolar_history");
        ResultSet rs = null;
        rs = stmt.executeQuery();
        while (rs.next()){
            valores.add(new DolarValue(rs.getTimestamp("date"), rs.getFloat("value")));
        }
        if (valores.isEmpty())
            return null;
        return valores;
    }

    public ArrayList<DolarValue> getLast500() throws SQLException{
        ArrayList<DolarValue> valores = new ArrayList<DolarValue>();
        PreparedStatement stmt = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select date, value from economy_dolar_history order by date desc limit 500;");
        ResultSet rs = null;
        rs = stmt.executeQuery();
        while (rs.next()){
            valores.add(new DolarValue(rs.getTimestamp("date"), rs.getFloat("value")));
        }
        if (valores.isEmpty())
            return null;
        return valores;
    }

}
