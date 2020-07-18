package Entities.System;

import java.sql.Timestamp;

public class SistemaLogLogins {

    private int id;
    private String usuario;
    private String password;
    private Timestamp tiempo;
    private Boolean resultado;

    public SistemaLogLogins(){

    }

    public SistemaLogLogins(String usuario){
        this.usuario = usuario;
    }

    public SistemaLogLogins(String usuario, String password, Boolean resultado) {
        this.usuario = usuario;
        this.password = password;
        this.resultado = resultado;
    }

    public SistemaLogLogins (String usuario, Boolean resultado){
        this.usuario = usuario;
        this.resultado = resultado;
    }

    public SistemaLogLogins(int id, String usuario, String password, Timestamp tiempo, Boolean resultado) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.tiempo = tiempo;
        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getTiempo() {
        return tiempo;
    }

    public void setTiempo(Timestamp tiempo) {
        this.tiempo = tiempo;
    }

    public Boolean getResultado() {
        return resultado;
    }

    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }
}
