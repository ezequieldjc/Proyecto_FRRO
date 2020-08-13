package Controladores;

import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaGlobalConfig;

import java.sql.SQLException;
import java.util.Random;

public class RegistrarUsuario {

    private String diccionario = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPRSTUVWXYZ0123456789";

    public PersonaEmpleado registrarUsuario (PersonaEmpleado p){

        try {
            p = this.generarUserID(p);
            p = this.generarPassword(p);
            p = this.generarEmail(p);
            p.setEstado(true);
            p = new DataPersonaEmpleado().ingresarUsuario(p);
            return p;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


    private PersonaEmpleado generarUserID (PersonaEmpleado personaEmpleado) {


        String usr = "" + personaEmpleado.getNombre().charAt(0);
        usr += personaEmpleado.getApellido().charAt(0);
        do {
            usr += String.valueOf(new Random().nextInt(10));
            usr += String.valueOf(new Random().nextInt(10));
            usr += String.valueOf(new Random().nextInt(10));
            usr += String.valueOf(new Random().nextInt(10));
            usr += String.valueOf(new Random().nextInt(10));
            personaEmpleado.setUsuario(usr);

        } while (new VerificarNuevoUserID().verificarUserID(personaEmpleado));
        return personaEmpleado;
    }

    private PersonaEmpleado generarPassword (PersonaEmpleado personaEmpleado){

        String pwd = "";
        for (int x = 0; x<10 ; x++){
            pwd += diccionario.charAt(new Random().nextInt(61));
        }
        System.out.println("LA pass es : " + pwd);
        personaEmpleado.setPass(new HashearPassword().hidePwd(pwd));
        return personaEmpleado;
    }

    private PersonaEmpleado generarEmail (PersonaEmpleado personaEmpleado){
        String email = "";

        SistemaGlobalConfig g =  new BuscarGlobalConfig().buscarGlobalConfigByCodigo(new SistemaGlobalConfig("DMN"));
        email = email +  personaEmpleado.getNombre().charAt(0) + personaEmpleado.getApellido() + "@" + g.getValorAtributo();
        personaEmpleado.setEmail(email);

        return personaEmpleado;
    }
}
