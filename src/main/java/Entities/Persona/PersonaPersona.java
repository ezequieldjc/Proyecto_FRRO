package Entities.Persona;

import java.util.Date;

public  abstract class PersonaPersona {

    private int id;
    private String nombre;
    private String apellido;
    private String celular;
    private String telefono;
    private String email;
    private Date fechaNacimiento;
    private PersonaDocumento doc;
    private String usuario;
    private String pass;
    private Boolean estado;
    private String img;

    public PersonaPersona(){

    }

    public PersonaPersona(int id) {
        this.id = id;
    }

    public PersonaPersona(String usr, String pass) {
        this.usuario = usr;
        this.pass = pass;
    }

    public PersonaPersona(String usr){
        this.usuario =usr;
    }

    public PersonaPersona(int id, String nombre, String lastName, String phone, String mobile, String email, Date birthDate, PersonaDocumento doc, String usr, String pass, Boolean status) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = lastName;
        this.celular = phone;
        this.telefono = mobile;
        this.email = email;
        this.fechaNacimiento = birthDate;
        this.doc = doc;
        this.usuario = usr;
        this.pass = pass;
        this.estado = status;
    }

    public PersonaPersona(int id, String nombre, String lastName, String phone, String mobile, String email, Date birthDate, PersonaDocumento doc, String usr, String pass, Boolean status, String img) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = lastName;
        this.celular = phone;
        this.telefono = mobile;
        this.email = email;
        this.fechaNacimiento = birthDate;
        this.doc = doc;
        this.usuario = usr;
        this.pass = pass;
        this.estado = status;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public PersonaDocumento getDoc() {
        return doc;
    }

    public void setDoc(PersonaDocumento doc) {
        this.doc = doc;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
