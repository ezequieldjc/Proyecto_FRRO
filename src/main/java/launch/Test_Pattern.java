package launch;

import Controladores.BuscarGlobalConfig;
import Controladores.TieneAccionPermitida;
import Data.DataPersonaEmpleado;
import Data.DataTipoDocumento;
import Entities.Persona.PersonaDocumento;
import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;
import Entities.System.SistemaAccion;
import Entities.System.SistemaGlobalConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class Test_Pattern {


    public static void main (String[] args){

        //String exp = "\\d{5}";
        String exp2 = "[a-zA-Z][a-zA-Z]\\d{5}";


        SistemaGlobalConfig f = new BuscarGlobalConfig().buscarGlobalConfigByCodigo(new SistemaGlobalConfig("USR_PTRN"));
        System.out.println(f.getValorAtributo());
        String exp = f.getValorAtributo();

        //System.out.println(Pattern.matches(exp, "9873a"));
        System.out.println(Pattern.matches(f.getValorAtributo(), "ED12345"));


        boolean flag = true;
        try {
            ArrayList<PersonaEmpleado> empleados =  new DataPersonaEmpleado().getAll();
            for (PersonaEmpleado e : empleados){
                if (!(Pattern.matches(new BuscarGlobalConfig().buscarGlobalConfigByCodigo(new SistemaGlobalConfig("USR_PTRN")).getValorAtributo(), "ED12345")))
                    flag=false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Flag: " + flag);


        System.out.println("------------");
        System.out.println(Pattern.matches(new BuscarGlobalConfig().buscarGlobalConfigByCodigo(new SistemaGlobalConfig("USR_PTRN")).getValorAtributo(), "ED99999"));
        System.out.println(Pattern.matches(new BuscarGlobalConfig().buscarGlobalConfigByCodigo(new SistemaGlobalConfig("USR_PTRN")).getValorAtributo(), "AM12345"));
        System.out.println(Pattern.matches(new BuscarGlobalConfig().buscarGlobalConfigByCodigo(new SistemaGlobalConfig("USR_PTRN")).getValorAtributo(), "PD00000"));
        System.out.println(Pattern.matches(new BuscarGlobalConfig().buscarGlobalConfigByCodigo(new SistemaGlobalConfig("USR_PTRN")).getValorAtributo(), "JD99999"));





    }



}
