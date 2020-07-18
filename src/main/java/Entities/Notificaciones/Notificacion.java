package Entities.Notificaciones;

import Entities.Persona.PersonaEmpleado;
import java.util.*;
import java.time.LocalDate;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notificacion {

    private int id;
    private NotificacionCategoria categoria;
    private String mensaje;
    private String urlDestino;
    private int prioridad;
    private PersonaEmpleado responsable;
    private int estado;
    private Timestamp fechaCreacion;

    public Notificacion(){

    }

    public Notificacion(int idCategoria, String msj,  int prioridad, String userResponsable){
        this.mensaje = msj;
        this.prioridad = prioridad;
        this.responsable = new PersonaEmpleado(userResponsable);
        this.categoria = new NotificacionCategoria(idCategoria);
    }

    public Notificacion(int id, NotificacionCategoria categoria, String mensaje, String urlDestino, int prioridad, PersonaEmpleado responsable, int estado, Timestamp fechaCreacion) {
        this.id = id;
        this.categoria = categoria;
        this.mensaje = mensaje;
        this.urlDestino = urlDestino;
        this.prioridad = prioridad;
        this.responsable = responsable;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public Notificacion(int id) {
        this.id = id;
    }

    public Notificacion(int id, NotificacionCategoria categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NotificacionCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(NotificacionCategoria categoria) {
        this.categoria = categoria;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUrlDestino() {
        return urlDestino;
    }

    public void setUrlDestino(String urlDestino) {
        this.urlDestino = urlDestino;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public PersonaEmpleado getResponsable() {
        return responsable;
    }

    public void setResponsable(PersonaEmpleado responsable) {
        this.responsable = responsable;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getTimeAgo(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return (int) ((timestamp.getTime() - this.fechaCreacion.getTime())/(60*1000));
    }
}

