package Entities;

public class Empresa {
    private int id;
    private String codigo;
    private String nombre;
    private String cuit;

    public Empresa(){

    }

    public Empresa(int id, String codigo, String nombre, String cuit) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.cuit = cuit;
    }

    public Empresa(String cuit) {
        this.cuit = cuit;
    }

    public Empresa(int id) {
        this.id = id;
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

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
}
