package Controladores;

import java.io.File;
import java.util.ArrayList;

public class BuscarNombresAvatar {

    public ArrayList<String> buscarPNGNames (){
        ArrayList<String> paths = new ArrayList<String>();
        String[] pngs = new File("/Users/ezequieldjemdjemian/Documents/PROYECTO_JAVA/web/images/avatar/").list();
        for (String file : pngs) {
            if (file.contains(".png")){
                paths.add(file);
            }
        }
        return paths;
    }
}
