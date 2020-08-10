package Data;

import Controladores.HashearPassword;
import Entities.Economy.DolarValue;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

}
