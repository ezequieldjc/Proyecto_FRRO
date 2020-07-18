package Entities.System;

public class SistemaAccion {

    private int id;
    private String codigo;
    private String nombre;
    private String desc;

    public SistemaAccion(){

    }

    public SistemaAccion(int id) {
        this.id = id;
    }

    public SistemaAccion(String codigo) {
        this.codigo = codigo;
    }

    public SistemaAccion(int id, String codigo, String nombre, String desc) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.desc = desc;
    }

    public SistemaAccion(String codigo, String nombre, String desc) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
