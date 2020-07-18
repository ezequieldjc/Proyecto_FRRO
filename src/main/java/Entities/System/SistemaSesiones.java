package Entities.System;

import Entities.Persona.PersonaEmpleado;

import java.sql.Timestamp;

public class SistemaSesiones {

    private int id;
    private PersonaEmpleado empleado;
    private Timestamp fechaDesde;
    private Timestamp fechaHasta;

    public SistemaSesiones(){

    }

    public SistemaSesiones(int id) {
        this.id = id;
    }

    public SistemaSesiones(int id, PersonaEmpleado empleado, Timestamp fechaDesde) {
        this.id = id;
        this.empleado = empleado;
        this.fechaDesde = fechaDesde;
    }

    public SistemaSesiones(int id, PersonaEmpleado empleado, Timestamp fechaDesde, Timestamp fechaHasta) {
        this.id = id;
        this.empleado = empleado;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersonaEmpleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(PersonaEmpleado empleado) {
        this.empleado = empleado;
    }

    public Timestamp getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Timestamp fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Timestamp getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Timestamp fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
}
