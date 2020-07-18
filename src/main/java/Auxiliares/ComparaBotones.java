package Auxiliares;

import Entities.System.SistemaBoton;

import java.util.ArrayList;

public class ComparaBotones {
    public boolean contiene(ArrayList<SistemaBoton> botones, SistemaBoton b){
        for (SistemaBoton bb: botones){
            if (bb.getId()==b.getId())
                    return true;
        }
        return false;
    }
}
