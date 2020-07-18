package Entities.System;

import Entities.Persona.PersonaEmpleado;

import java.sql.Timestamp;

public class SistemaMensaje {
    private int id;
    private SistemaModulo SistemaModulo;
    private String mensaje;
    private Timestamp tiempo;
    private PersonaEmpleado empleado;

    public SistemaMensaje(){

    }

    public SistemaMensaje(String codModulo, String usuario, String mensaje){
        this.setSistemaModulo(new SistemaModulo(codModulo));
        this.setEmpleado(new PersonaEmpleado(usuario));
        this.setMensaje(mensaje);
    }

    public SistemaMensaje(int id) {
        this.id = id;
    }

    public SistemaMensaje(int id, SistemaModulo sistemaModulo, String mensaje, Timestamp tiempo) {
        this.id = id;
        this.SistemaModulo = sistemaModulo;
        this.mensaje = mensaje;
        this.tiempo = tiempo;
    }

    public SistemaMensaje(int id, SistemaModulo sistemaModulo, String mensaje, Timestamp tiempo, PersonaEmpleado empleado) {
        this.id = id;
        this.SistemaModulo = sistemaModulo;
        this.mensaje = mensaje;
        this.tiempo = tiempo;
        this.empleado = empleado;
    }

    public SistemaMensaje(SistemaModulo sistemaModulo, String mensaje) {
        SistemaModulo = sistemaModulo;
        this.mensaje = mensaje;
    }

    public SistemaMensaje(SistemaModulo sistemaModulo, String mensaje, PersonaEmpleado empleado) {
        this.SistemaModulo = sistemaModulo;
        this.mensaje = mensaje;
        this.empleado = empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SistemaModulo getSistemaModulo() {
        return SistemaModulo;
    }

    public void setSistemaModulo(SistemaModulo sistemaModulo) {
        this.SistemaModulo = sistemaModulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timestamp getTiempo() {
        return tiempo;
    }

    public void setTiempo(Timestamp tiempo) {
        this.tiempo = tiempo;
    }

    public PersonaEmpleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(PersonaEmpleado empleado) {
        this.empleado = empleado;
    }
}
