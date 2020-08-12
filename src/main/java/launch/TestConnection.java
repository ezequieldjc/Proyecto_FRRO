package launch;

import Data.DataConnectioniMac;

import java.sql.Connection;

public class TestConnection {

    public static void main (String[] args){
        Connection con = DataConnectioniMac.getInstancia().getConn();
        System.out.println(con==null);
        System.out.println(con.toString());

    }
}
