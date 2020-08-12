package Controladores;

import Entities.Persona.PersonaEmpleado;

import java.util.Random;

public class GenerarUserID {

    public String generarUserID (PersonaEmpleado personaEmpleado) {

        String usr = "" + personaEmpleado.getNombre().charAt(0);
        usr += personaEmpleado.getApellido().charAt(0);
        usr += new Random().nextInt();
        usr += String.valueOf(new Random().nextInt(10));
        usr += String.valueOf(new Random().nextInt(10));
        usr += String.valueOf(new Random().nextInt(10));
        usr += String.valueOf(new Random().nextInt(10));
        usr += String.valueOf(new Random().nextInt(10));





        return "";


    }
}
