package Entities.Persona;

public class PersonaDocumento {

    private int id;
    private String codigo;
    private String nombre;
    private String desc;
    private String numero;

    public PersonaDocumento(){

    }

    public PersonaDocumento(int id) {
        this.id = id;
    }

    public PersonaDocumento(String codigo) {
        this.codigo = codigo;
    }

    public PersonaDocumento(int id, String codigo, String nombre, String desc, String numero) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.desc = desc;
        this.numero = numero;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
