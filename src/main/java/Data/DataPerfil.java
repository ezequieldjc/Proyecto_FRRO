package Data;

import Entities.Persona.PersonaEmpleado;
import Entities.Persona.PersonaPerfil;
import Entities.System.SistemaAccion;
import Entities.System.SistemaBoton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataPerfil {

    public ArrayList<PersonaPerfil> getAll() throws SQLException{
        ArrayList<PersonaPerfil> perfiles = new ArrayList<PersonaPerfil>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT pp.id, pe2.usuario usuarioBackup, pe.usuario usuarioOwner, pp.codigo, pp.nombre, pp.desc " +
                "FROM persona_perfil pp " +
                "left join persona_empleado pe " +
                "on pp.idOwner = pe.id " +
                "left join persona_empleado pe2 " +
                "on pp.idBackup = pe2.id ;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            perfiles.add( new PersonaPerfil(rs.getInt("id"), rs.getString("pp.codigo"), rs.getString("pp.nombre"),
                    rs.getString("pp.desc"), new PersonaEmpleado( rs.getString("usuarioOwner")), new PersonaEmpleado( rs.getString("usuarioBackup"))));
        }
        return perfiles;
    }

    public HashMap<Integer, String> getHashNombres () throws SQLException {
        HashMap<Integer, String> usuarios = new HashMap<Integer, String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select id, nombre from persona_perfil";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
            while (rs.next()){
            usuarios.put(rs.getInt("id"), rs.getString("nombre"));
        }
            return usuarios;
    }

    public ArrayList<PersonaPerfil> getAllByFilter(PersonaPerfil p) throws SQLException{
        ArrayList<PersonaPerfil> perfiles = new ArrayList<PersonaPerfil>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String where = "";
        if ( p.getId()>0 ){
            where += "where pp.id = "  + p.getId();
        }
        String query = "SELECT pp.id, pe2.usuario usuarioBackup, pe.usuario usuarioOwner, pp.codigo, pp.nombre, pp.desc " +
                "FROM persona_perfil pp " +
                "left join persona_empleado pe " +
                "on pp.idOwner = pe.id " +
                "left join persona_empleado pe2 " +
                "on pp.idBackup = pe2.id " + where + " ;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            perfiles.add( new PersonaPerfil(rs.getInt("id"), rs.getString("pp.codigo"), rs.getString("pp.nombre"),
                    rs.getString("pp.desc"), new PersonaEmpleado( rs.getString("usuarioOwner")), new PersonaEmpleado( rs.getString("usuarioBackup"))));
        }
        return perfiles;
    }

    public PersonaPerfil getOneByID(PersonaPerfil p) throws SQLException{

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT pp.id, pe2.usuario usuarioBackup, pe.usuario usuarioOwner, pp.codigo, pp.nombre, pp.desc " +
                "FROM persona_perfil pp " +
                "left join persona_empleado pe " +
                "on pp.idOwner = pe.id " +
                "left join persona_empleado pe2 " +
                "on pp.idBackup = pe2.id " +
                "where pp.id = ?;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        stmt.setInt(1, p.getId());
        rs = stmt.executeQuery();
        if(rs.next()){
            PersonaPerfil pp =  new PersonaPerfil(rs.getInt("pp.id"), rs.getString("pp.codigo"), rs.getString("pp.nombre"),
                    rs.getString("pp.desc"), new PersonaEmpleado( rs.getString("usuarioOwner")), new PersonaEmpleado( rs.getString("usuarioBackup")));
            pp.setAcciones(new DataAccion().getAllByPerfil(pp));
            pp.setBotones(new DataBoton().getAllByPerfil(pp));
            return pp;
        }
        PersonaPerfil pp = new PersonaPerfil(p.getId());
        return pp;
    }

    public PersonaPerfil getOneByID_v2(PersonaPerfil p) throws SQLException{
        //A diferencia de getOneByID este devuelve los ID del owner y del backup (el otro metodo devuelve los usuarios).
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT pp.id, pe2.id usuarioBackup, pe.id usuarioOwner, pp.codigo, pp.nombre, pp.desc " +
                "FROM persona_perfil pp " +
                "left join persona_empleado pe " +
                "on pp.idOwner = pe.id " +
                "left join persona_empleado pe2 " +
                "on pp.idBackup = pe2.id " +
                "where pp.id = ?;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        stmt.setInt(1, p.getId());
        rs = stmt.executeQuery();
        if(rs.next()){
            PersonaPerfil pp =  new PersonaPerfil(rs.getInt("pp.id"), rs.getString("pp.codigo"), rs.getString("pp.nombre"),
                    rs.getString("pp.desc"), new PersonaEmpleado( rs.getInt("usuarioOwner")), new PersonaEmpleado( rs.getInt("usuarioBackup")));
            pp.setAcciones(new DataAccion().getAllByPerfil(pp));
            pp.setBotones(new DataBoton().getAllByPerfil(pp));
            return pp;
        }
        PersonaPerfil pp = new PersonaPerfil(p.getId());
        return pp;
    }

    public PersonaPerfil actualizar(PersonaPerfil p) throws SQLException{
        PersonaPerfil pOriginal = this.getOneByID_v2(p);
        String set = "set id=id";
        if (! pOriginal.getCodigo().equals(p.getCodigo())){
            set += " , codigo = '"+p.getCodigo()+"'";
        }
        if (! pOriginal.getName().equals(p.getName())){
            set += " , nombre = '"+p.getName()+"'";
        }
        if (! pOriginal.getDesc().equals(p.getDesc())){
            set += " , `desc` = '"+p.getDesc()+"'";
        }
        if (pOriginal.getOwner().getId() != p.getOwner().getId()){
            if (p.getOwner().getId()==0)
                    set += " , idOwner = null";
            else
                set += " , idOwner = "+ p.getOwner().getId() ;
        }
        System.out.println(" owner original: " + pOriginal.getOwner().getId());
        System.out.println(" owner nuevo: " + p.getOwner().getId());
        System.out.println(pOriginal.getOwner().getId() == p.getOwner().getId());
        System.out.println("");
        System.out.println(" backup original: " + pOriginal.getBackup().getId());
        System.out.println(" backup nuevo: " + p.getBackup().getId());
        System.out.println(pOriginal.getBackup().getId() == p.getBackup().getId());

        if (pOriginal.getBackup().getId() != p.getBackup().getId()){
            if (p.getBackup().getId()==0)
                set += " , idBackup = null";
            else
                set += " , idBackup = "+ p.getBackup().getId() ;
        }

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println(set);
        System.out.println("-----------------------------------------------------------------------------");

        System.out.println(p.getId());
        System.out.println(p.getCodigo());
        System.out.println(p.getName());
        System.out.println(p.getDesc());
        System.out.println(p.getOwner().getId());
        System.out.println(p.getBackup().getId());
        for (SistemaBoton b : p.getBotones()){
            System.out.println(b.getId());
        }
        for (SistemaAccion a : p.getAcciones()){
            System.out.println(a.getId());
        }
        //String query = "update persona_perfil set codigo = ? where id = ? ";




        return p;
    }

}
