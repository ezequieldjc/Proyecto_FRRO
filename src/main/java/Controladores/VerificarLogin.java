package Controladores;

import Entities.Persona.PersonaEmpleado;

import javax.xml.crypto.Data;
import Data.DataPersonaEmpleado;

import java.sql.SQLException;

public class VerificarLogin {

    public PersonaEmpleado esEmpleadoValido (PersonaEmpleado emp){
        try{
            emp = new DataPersonaEmpleado().getByUsrYPass(emp);
            if (emp!=null){
                return emp;
            } else {
                return null;
            }
        } catch (SQLException e ){
            e.printStackTrace();
            return null;
        }
    }



}
