package Controladores;

import Data.DataGlobalConfig;
import Entities.System.SistemaGlobalConfig;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuscarGlobalConfig {

    public SistemaGlobalConfig buscarGlobalConfigByID(SistemaGlobalConfig g){
        try {
            return new DataGlobalConfig().getOneByID(g);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public SistemaGlobalConfig buscarGlobalConfigByCodigo(SistemaGlobalConfig g){
        try {
            return new DataGlobalConfig().getOneByCodigo(g);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<SistemaGlobalConfig> buscarGlobalConfigByFilter(SistemaGlobalConfig g){
        try {
            return new DataGlobalConfig().getByFilter(g);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
