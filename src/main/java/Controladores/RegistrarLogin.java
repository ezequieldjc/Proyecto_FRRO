package Controladores;

import Data.DataLogLogins;
import Entities.System.SistemaLogLogins;

public class RegistrarLogin {

    public RegistrarLogin (String usr, String pass, Boolean rdo){
        new DataLogLogins().registrarLogin(new SistemaLogLogins(usr, pass, rdo));
    }

    public RegistrarLogin(SistemaLogLogins l){
        new DataLogLogins().registrarLogin(l);
    }

}
