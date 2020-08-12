package Data;

import Controladores.BuscarNotificaciones;
import Entities.Persona.PersonaDocumento;
import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataTipoDocumento {

    public ArrayList<PersonaDocumento> getAll() throws SQLException {
        ArrayList<PersonaDocumento> personaDocumentos = new ArrayList<PersonaDocumento>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(" SELECT `persona_tipo_documento`.`id`," +
                "    `persona_tipo_documento`.`codigo`," +
                "    `persona_tipo_documento`.`nombre`," +
                "    `persona_tipo_documento`.`desc`" +
                " FROM `proyecto-mysql`.`persona_tipo_documento`; ");
        rs = stmt.executeQuery();
        while (rs.next()){
            PersonaDocumento pd = new PersonaDocumento();
            pd.setCodigo(rs.getString("codigo"));
            pd.setId(rs.getInt("id"));
            pd.setNombre("nombre");
            pd.setDesc("desc");
            personaDocumentos.add(pd);
        }
        if (personaDocumentos.isEmpty())
            return null;
        return personaDocumentos;
    }

    public ArrayList<PersonaDocumento> getOneByID(PersonaDocumento d) throws SQLException {
        ArrayList<PersonaDocumento> personaDocumentos = new ArrayList<PersonaDocumento>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(" SELECT `persona_tipo_documento`.`id`," +
                "    `persona_tipo_documento`.`codigo`," +
                "    `persona_tipo_documento`.`nombre`," +
                "    `persona_tipo_documento`.`desc`" +
                " FROM `proyecto-mysql`.`persona_tipo_documento`" +
                " where `persona_tipo_documento`.`id` = ? ; ");
        stmt.setInt(1,d.getId());
        rs = stmt.executeQuery();
        while (rs.next()){
            PersonaDocumento pd = new PersonaDocumento();
            pd.setCodigo(rs.getString("codigo"));
            pd.setId(rs.getInt("id"));
            pd.setNombre("nombre");
            pd.setDesc("desc");
            personaDocumentos.add(pd);
        }
        if (personaDocumentos.isEmpty())
            return null;
        return personaDocumentos;
    }

}
