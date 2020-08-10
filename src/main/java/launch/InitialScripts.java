package launch;

import Controladores.Economy.Dolar_Operations;
import Controladores.JSON.Dolar_Handler;
import Data.DataEconomyDolar;
import Entities.Economy.DolarValue;

import java.sql.SQLException;
import java.util.ArrayList;

public class InitialScripts {

    public void insertAllDolarValues()  {
        Dolar_Operations d = new Dolar_Operations();
        String valuelist= d.getDolarValueFromBCRA();

        //System.out.println(valuelist);

        Dolar_Handler dh = new Dolar_Handler();
        ArrayList<DolarValue> dolarList = dh.serialize(valuelist);

        Dolar_Operations ded = new Dolar_Operations();
        ded.insertDolarValue(dolarList);
    }
}
