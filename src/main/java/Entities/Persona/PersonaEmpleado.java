package Entities.Persona;

import Entities.Notificaciones.Notificacion;

import java.util.ArrayList;
import java.util.Date;

public class PersonaEmpleado extends PersonaPersona {

    private PersonaPerfil perfil;
    private ArrayList<Notificacion> notificaciones;

    public PersonaEmpleado(){

    }

    public PersonaEmpleado(PersonaPerfil perfil){
        this.perfil = perfil;
    }

    public PersonaEmpleado(String usr){
        super(usr);
    }

    public PersonaEmpleado(int id) {
        super(id);
    }

    public PersonaEmpleado(int id, String usr){
        this.setId(id);
        this.setUsuario(usr);
    }

    public PersonaEmpleado(String usr, String pass) {
        super(usr, pass);
        this.perfil = perfil;
    }

    public PersonaEmpleado(int id, String usr, String nombre, String apellido){
        super(id);
        super.setUsuario(usr);
        super.setNombre(nombre);
        super.setApellido(apellido);

    }

    public PersonaEmpleado(int id, String fistName, String lastName, String phone, String mobile, String email,
                           Date birthDate, PersonaDocumento doc, String usr, String pass, Boolean status,
                           PersonaPerfil perfil, ArrayList<Notificacion> notificaciones, String img) {
        super(id, fistName, lastName, phone, mobile, email, birthDate, doc, usr, pass, status, img);
        this.perfil = perfil;
        this.notificaciones=notificaciones;
    }

    public PersonaPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(PersonaPerfil perfil) {
        this.perfil = perfil;
    }

    public ArrayList<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(ArrayList<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }
}
