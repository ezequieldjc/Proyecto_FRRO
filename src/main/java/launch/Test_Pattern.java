package launch;

import Controladores.BuscarGlobalConfig;
import Controladores.HashearPassword;
import Controladores.TieneAccionPermitida;
import Controladores.VerificarDate;
import Data.DataPersonaEmpleado;
import Data.DataTipoDocumento;
import Entities.Persona.PersonaDocumento;
import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;
import Entities.System.SistemaAccion;
import Entities.System.SistemaGlobalConfig;
import org.apache.commons.validator.GenericValidator;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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


        SistemaGlobalConfig ff = new BuscarGlobalConfig().buscarGlobalConfigByCodigo(new SistemaGlobalConfig("TFM_PTRN"));
        System.out.println(Pattern.matches(ff.getValorAtributo(), "2019/2/18"));



    }



}
