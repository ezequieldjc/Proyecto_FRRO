package Auxiliares;


import Entities.System.SistemaBoton;

import java.util.ArrayList;

public class FuncionesAuxiliares {

    public String deleteBlanks (String a){
        String nuevo = "";
        for (int i=0 ; i<a.length(); i++){
            if(a.charAt(i) != ' ')
                nuevo = nuevo + a.charAt(i);
        }
        return nuevo;
    }

    public Boolean tieneHijos (ArrayList<SistemaBoton> botones, int id){
        Boolean rdo = false;
        return false;
    }
}
