package Controladores;

import Data.DataPerfil;
import Data.DataTipoDocumento;
import Entities.Persona.PersonaDocumento;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class BuscarTipoDoc {

    public ArrayList<PersonaDocumento> getAll(){
        try {
            return new DataTipoDocumento().getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
