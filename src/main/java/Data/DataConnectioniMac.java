package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;

public class DataConnectioniMac {
    private String driver = "com.mysql.cj.jdbc.Driver";
    //private String driver = "com.mysql.jdbc.Driver";

    //Google Cloud
    private String host = "35.247.241.104";
    private String db = "proyecto-mysql";

    //Local
    //private String host = "localhost";
    //private String db = "PROYECTO_JAVA";

    private String port = "3306";
    private String user = "root";
    private String passowrd = "c3l9s3b7";
    private int conectados = 0;
    private Connection conn = null;
    private static DataConnectioniMac instancia;
    //el collate que va : utf8mb4_general_ci
    private DataConnectioniMac(){
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DataConnectioniMac getInstancia(){
        if (instancia==null){
            instancia = new DataConnectioniMac();
        }
        return instancia;
    }
    public Connection getConn(){
        try{
            if (conn==null || conn.isClosed()){
                conn= DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?autoReconnect=true&useSSL=false",user,passowrd);
                conectados = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conectados ++;
        return conn;
    }

    public void releaseConn (){
        conectados --;
        try {
            if (conectados<=0){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
