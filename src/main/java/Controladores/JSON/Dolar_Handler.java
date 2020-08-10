package Controladores.JSON;

import Entities.Economy.DolarValue;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Dolar_Handler {

    public ArrayList<DolarValue> serialize (String gson){
        Gson g = new Gson();
        DolarValue dv[] = g.fromJson(gson, DolarValue[].class);

        //Convertir el [] a arraylist
        ArrayList<DolarValue> valueList = new ArrayList<DolarValue>(100000000);
        for (int x = 0 ; x < dv.length; x++){
            valueList.add(new DolarValue(dv[x].getFecha(), dv[x].getValue()));
        }
        return valueList;
    }

    public String deserialize (ArrayList<DolarValue> valueList){
        // no creo necesitar este metodo
        return "a";
    }



}
