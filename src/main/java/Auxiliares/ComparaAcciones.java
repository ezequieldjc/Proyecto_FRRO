package Auxiliares;

import Entities.System.SistemaAccion;
import Entities.System.SistemaBoton;

import java.util.ArrayList;

public class ComparaAcciones {

    public boolean contiene(ArrayList<SistemaAccion> acciones, SistemaAccion a){
        for (SistemaAccion acc: acciones){
            System.out.println("ainaina");
            if (acc.getId()==a.getId())
                return true;
        }
        return false;
    }
}
