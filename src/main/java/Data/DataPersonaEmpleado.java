package Data;

import Controladores.BuscarNotificaciones;
import Controladores.HashearPassword;
import Controladores.ManejoSesiones;
import Entities.Persona.PersonaPerfil;
import Entities.Persona.PersonaDocumento;
import Entities.Persona.PersonaEmpleado;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DataPersonaEmpleado {

    public ArrayList<PersonaEmpleado> getAll() throws SQLException{
        ArrayList<PersonaEmpleado> empleados = new ArrayList<PersonaEmpleado>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select e.id, tp.id, tp.codigo, e.usuario,tp.nombre, tp.desc, e.nroDocumento, e.nombre, e.apellido, e.celular, e.telefono, e.email, e.fechaNacimiento, e.estado, e.img, c.password, p.id, p.idOwner, p.idBackup, p.codigo, p.nombre, p.desc " +
                    "from persona_empleado e " +
                    "left join persona_contrasena c " +
                    "on e.id = c.idEmpleado " +
                    "left join persona_perfil p " +
                    "on e.idPerfil = p.id " +
                    "left join persona_tipo_documento tp " +
                    "on e.idTipoDocumento = tp.id " +
                    "where c.esActual = true");
        rs = stmt.executeQuery();
        while (rs.next()){
            PersonaEmpleado e = new PersonaEmpleado();
            //setear campos basicos
            e.setId(rs.getInt("e.id"));
            e.setUsuario(rs.getString("e.usuario"));
            e.setNombre(rs.getString("e.nombre"));
            e.setApellido(rs.getString("e.apellido"));
            e.setTelefono(rs.getString("e.telefono"));
            e.setCelular(rs.getString("e.telefono"));
            e.setEmail(rs.getString("e.email"));
            e.setFechaNacimiento(null);
            e.setEstado(rs.getBoolean("e.estado"));
            e.setPass(rs.getString("c.password"));
            e.setImg(rs.getString("e.img"));
            //Setear documento
            e.setDoc(new PersonaDocumento(rs.getInt("tp.id"), rs.getString("tp.codigo"), rs.getString("tp.nombre"),rs.getString("tp.desc"), rs.getString("e.nroDocumento")));
            //setear perfil
            e.setPerfil(new PersonaPerfil(rs.getInt("p.id"),rs.getString("p.codigo"),rs.getString("p.nombre"), rs.getString("p.desc"), new PersonaEmpleado(rs.getString("p.idOwner")),new PersonaEmpleado(rs.getString("p.idBackup"))));
            e.setNotificaciones(new BuscarNotificaciones().getUnreadByUsuario(e));
            empleados.add(e);
        }
        if (empleados.isEmpty())
            return null;
        return empleados;
    }

    public PersonaEmpleado getByUsuario(PersonaEmpleado emp) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select e.id, e.usuario,tp.id, tp.codigo, tp.nombre, tp.desc, e.nroDocumento, e.nombre, e.apellido, e.celular, e.telefono, e.email, e.fechaNacimiento, e.estado, e.img, c.password, p.id, p.idOwner, p.idBackup, p.codigo, p.nombre, p.desc " +
                    "from persona_empleado e " +
                    "left join persona_contrasena c " +
                    "on e.id = c.idEmpleado " +
                    "left join persona_perfil p " +
                    "on e.idPerfil = p.id " +
                    "left join persona_tipo_documento tp " +
                    "on e.idTipoDocumento = tp.id " +
                    "where e.usuario = ? and c.esActual = true");
        stmt.setString(1,emp.getUsuario());
        rs = stmt.executeQuery();
        if (rs.next()){
            PersonaEmpleado e = new PersonaEmpleado();
            //setear campos basicos
            e.setId(rs.getInt("e.id"));
            e.setUsuario(rs.getString("e.usuario"));
            e.setNombre(rs.getString("e.nombre"));
            e.setApellido(rs.getString("e.apellido"));
            e.setTelefono(rs.getString("e.telefono"));
            e.setCelular(rs.getString("e.telefono"));
            e.setEmail(rs.getString("e.email"));
            e.setFechaNacimiento(null);
            e.setEstado(rs.getBoolean("e.estado"));
            e.setPass(rs.getString("c.password"));
            e.setImg(rs.getString("e.img"));
            //Setear documento
            e.setDoc(new PersonaDocumento(rs.getInt("tp.id"), rs.getString("tp.codigo"), rs.getString("tp.nombre"),rs.getString("tp.desc"), rs.getString("e.nroDocumento")));
            //setear perfil
            e.setPerfil(new PersonaPerfil(rs.getInt("p.id"),rs.getString("p.codigo"),rs.getString("p.nombre"), rs.getString("p.desc"), new PersonaEmpleado(rs.getString("p.idOwner")),new PersonaEmpleado(rs.getString("p.idBackup"))));
            e.setNotificaciones(new BuscarNotificaciones().getUnreadByUsuario(e));
            return e;
        }
        else {
            return null;
        }
    }

    public PersonaEmpleado getByUsrYPass (PersonaEmpleado emp) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select e.id, e.usuario, tp.id, tp.codigo, tp.nombre, tp.desc, e.nroDocumento," +
                " e.nombre, e.apellido, e.celular, e.telefono, e.email, e.img, e.fechaNacimiento, e.estado, c.password, p.id, p.idOwner, p.idBackup, p.codigo, " +
                "p.nombre, p.desc " +
                "from persona_empleado e " +
                "left join persona_contrasena c " +
                "on e.id = c.idEmpleado " +
                "left join persona_perfil p " +
                "on e.idPerfil = p.id " +
                "left join persona_tipo_documento tp " +
                "on e.idTipoDocumento = tp.id " +
                "where e.usuario = ? and c.esActual = true");
        stmt.setString(1,emp.getUsuario());
        rs = stmt.executeQuery();
        if (rs.next()){
            if (new HashearPassword().unHidePwd(rs.getString("c.password")).equals(emp.getPass())) {
                PersonaEmpleado e = new PersonaEmpleado();
                //setear campos basicos
                e.setId(rs.getInt("e.id"));
                e.setNombre(rs.getString("e.nombre"));
                e.setUsuario(rs.getString("e.usuario"));
                e.setApellido(rs.getString("e.apellido"));
                e.setTelefono(rs.getString("e.telefono"));
                e.setCelular(rs.getString("e.telefono"));
                e.setEmail(rs.getString("e.email"));
                e.setFechaNacimiento(null);
                e.setEstado(rs.getBoolean("e.estado"));
                e.setPass(rs.getString("c.password"));
                e.setImg(rs.getString("e.img"));
                //Setear documento
                e.setDoc(new PersonaDocumento(rs.getInt("tp.id"), rs.getString("tp.codigo"), rs.getString("tp.nombre"), rs.getString("tp.desc"), rs.getString("e.nroDocumento")));
                //setear perfil
                e.setPerfil(new PersonaPerfil(rs.getInt("p.id"), rs.getString("p.codigo"), rs.getString("p.nombre"), rs.getString("p.desc"), new PersonaEmpleado(rs.getString("p.idOwner")), new PersonaEmpleado(rs.getString("p.idBackup"))));
                e.setNotificaciones(new BuscarNotificaciones().getUnreadByUsuario(e));
                return e;
            }
        }
        return null;
    }

    public PersonaEmpleado getByID(PersonaEmpleado emp) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                "select e.id, e.usuario,tp.id, tp.codigo, tp.nombre, tp.desc, e.nroDocumento, e.img, e.nombre, e.apellido, e.celular, e.telefono, e.email, e.fechaNacimiento, e.estado, c.password, p.id, p.idOwner, p.idBackup, p.codigo, p.nombre, p.desc " +
                "from persona_empleado e " +
                "left join persona_contrasena c " +
                "on e.id = c.idEmpleado " +
                "left join persona_perfil p " +
                "on e.idPerfil = p.id " +
                "left join persona_tipo_documento tp " +
                "on e.idTipoDocumento = tp.id " +
                "where e.id = ? and c.esActual = true");
        stmt.setInt(1,emp.getId());
        rs = stmt.executeQuery();

        if (rs.next()){
            PersonaEmpleado e = new PersonaEmpleado();
            //setear campos basicos
            e.setId(rs.getInt("e.id"));
            e.setUsuario(rs.getString("e.usuario"));
            e.setNombre(rs.getString("e.nombre"));
            e.setApellido(rs.getString("e.apellido"));
            e.setTelefono(rs.getString("e.telefono"));
            e.setCelular(rs.getString("e.telefono"));
            e.setEmail(rs.getString("e.email"));
            e.setFechaNacimiento(null);
            e.setEstado(rs.getBoolean("e.estado"));
            e.setPass(rs.getString("c.password"));
            e.setImg(rs.getString("e.img"));
            //Setear documento
            e.setDoc(new PersonaDocumento(rs.getInt("tp.id"), rs.getString("tp.codigo"), rs.getString("tp.nombre"),rs.getString("tp.desc"), rs.getString("e.nroDocumento")));
            //setear perfil
            e.setPerfil(new PersonaPerfil(rs.getInt("p.id"),rs.getString("p.codigo"),rs.getString("p.nombre"), rs.getString("p.desc"),
                    new PersonaEmpleado(rs.getString("p.idOwner")),new PersonaEmpleado(rs.getString("p.idBackup"))));
            PersonaPerfil p = e.getPerfil();
            p.setAcciones(new DataAccion().getAllByEmpleado(e));
            p.setBotones(new DataBoton().getAllByEmpleado(e));
            e.setPerfil(p);
            e.setNotificaciones(new BuscarNotificaciones().getUnreadByUsuario(e));
            return e;
        }
        else {
            return null;
        }
    }

    public ArrayList<PersonaPerfil> getAllPerfiles () throws SQLException{
        ArrayList<PersonaPerfil> perfiles = new ArrayList<PersonaPerfil>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("" +
                "select id, idOwner, idBackup, codigo, nombre, `desc` " +
                "from persona_perfil");
        rs = stmt.executeQuery();
        while (rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            perfiles.add(new PersonaPerfil(rs.getInt("id"), rs.getString("codigo"), rs.getString("nombre"), rs.getString("desc"), new PersonaEmpleado(rs.getInt("idOwner")), new PersonaEmpleado(rs.getInt("idBackup"))));
        }
        if (perfiles.isEmpty())
            return null;
        return perfiles;
    }

    public ArrayList<PersonaEmpleado> getAllByFilter (String user, String rol, String status ) throws SQLException {
        ArrayList<PersonaEmpleado> empleados = new ArrayList<PersonaEmpleado>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        String clausula = "where c.esActual = true ";
        if (!user.equals(""))
            clausula = clausula + " and e.usuario like "+"\"%"+user+"%\"";
        if (!rol.equals(""))
            clausula = clausula + " and p.id = "+rol;
        if(status.equals("2"))
            clausula = clausula + " and not e.estado " ;
        else if (status.equals("1"))
            clausula = clausula + " and e.estado " ;
        String query = "select e.id, e.usuario ,tp.id, tp.codigo, tp.nombre, tp.desc, e.nroDocumento, e.nombre, e.apellido, e.celular, e.telefono, e.img, e.email, e.fechaNacimiento, e.estado, c.password, p.id, p.idOwner, p.idBackup, p.codigo, p.nombre, p.desc " +
                "from persona_empleado e " +
                "left join persona_contrasena c " +
                "on e.id = c.idEmpleado " +
                "left join persona_perfil p " +
                "on e.idPerfil = p.id " +
                "left join persona_tipo_documento tp " +
                "on e.idTipoDocumento = tp.id " + clausula;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()){
            PersonaEmpleado e = new PersonaEmpleado();
            //setear campos basicos
            e.setId(rs.getInt("e.id"));
            e.setUsuario(rs.getString("e.usuario"));
            e.setNombre(rs.getString("e.nombre"));
            e.setApellido(rs.getString("e.apellido"));
            e.setTelefono(rs.getString("e.telefono"));
            e.setCelular(rs.getString("e.telefono"));
            e.setEmail(rs.getString("e.email"));
            e.setFechaNacimiento(null);
            e.setEstado(rs.getBoolean("e.estado"));
            e.setPass(rs.getString("c.password"));
            e.setImg(rs.getString("e.img"));
            //Setear documento
            e.setDoc(new PersonaDocumento(rs.getInt("tp.id"), rs.getString("tp.codigo"), rs.getString("tp.nombre"),rs.getString("tp.desc"), rs.getString("e.nroDocumento")));
            //setear perfil
            e.setPerfil(new PersonaPerfil(rs.getInt("p.id"),rs.getString("p.codigo"),rs.getString("p.nombre"), rs.getString("p.desc"), new PersonaEmpleado(rs.getString("p.idOwner")),new PersonaEmpleado(rs.getString("p.idBackup"))));
            e.setNotificaciones(new BuscarNotificaciones().getUnreadByUsuario(e));
            empleados.add(e);
        }
        return empleados;
    }

    public boolean cambiarEstado (PersonaEmpleado e) throws SQLException{
        PreparedStatement stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                "update persona_empleado set estado = not estado where id = ?");
        stmt.setInt(1, e.getId());
        stmt.executeUpdate();
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement("select estado from persona_empleado where id = ?");
        stmt.setInt(1, e.getId());
        ResultSet rs = stmt.executeQuery();
        rs.next();
        Boolean status = rs.getBoolean("estado");
        if (!status)
            new ManejoSesiones().limpiarSesion(e);
        e.setEstado(status);
        /*if (status){
            RecordMessage rm = new RecordMessage("ABMUsuarios", "Se deshabilito al usuario "+e.getUsr(),usr);
        }else {
            RecordMessage rm = new RecordMessage("ABMUsuarios", "Se habilito al usuario "+e.getUsr(),usr);
        }*/
        return status;
    }

    public HashMap<Integer, String> getAllNombresUsuarios() throws SQLException{
        HashMap<Integer, String> usuarios = new HashMap<Integer, String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select id, usuario from persona_empleado";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()){
            usuarios.put(rs.getInt("id"), rs.getString("usuario"));
        }
        return usuarios;
    }

    public void actualizar (PersonaEmpleado e) throws SQLException {
        String set = " set id = id";
        if (e.getPerfil().getId()!=0){
            set += " , idPerfil = "+e.getPerfil().getId();
        }
        if(!(e.getTelefono().equals(""))){
            set += " , telefono = " + e.getTelefono();
        }
        if(!(e.getCelular().equals(""))){
            set += " , celular =  " + e.getCelular();
        }
        if (!(e.getEmail().equals(""))){
            set+= " , email = '" + e.getEmail() + "'";
        }

        String query = "update persona_empleado " + set + " where id="+e.getId()+"; ";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
            DataConnectioniMac.getInstancia().getConn().setAutoCommit(false);
            stmt.executeUpdate();
            DataConnectioniMac.getInstancia().getConn().commit();
        } catch (SQLException ef){
            DataConnectioniMac.getInstancia().getConn().rollback();
        }
    }

    public void actualizarImagen (PersonaEmpleado e) throws SQLException {
        String set = " set id = id";
        if (e.getPerfil().getId()!=0){
            set += " , idPerfil = "+e.getPerfil().getId();
        }
        if(!(e.getTelefono().equals(""))){
            set += " , telefono = " + e.getTelefono();
        }
        if(!(e.getCelular().equals(""))){
            set += " , celular =  " + e.getCelular();
        }
        if (!(e.getEmail().equals(""))){
            set+= " , email = '" + e.getEmail() + "'";
        }

        String query = "update persona_empleado set img = ?  where id=? ; ";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
            DataConnectioniMac.getInstancia().getConn().setAutoCommit(false);
            stmt.setString(1, e.getImg());
            stmt.setInt(2, e.getId());
            stmt.executeUpdate();
            DataConnectioniMac.getInstancia().getConn().commit();
        } catch (SQLException ef){
            DataConnectioniMac.getInstancia().getConn().rollback();
        }
    }


}
