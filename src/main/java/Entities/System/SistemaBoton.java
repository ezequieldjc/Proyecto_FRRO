package Entities.System;

public class SistemaBoton {

    private int id;
    private String codigo;
    private String nombre;
    private int idPadre;
    private float valor;
    private boolean habilitado;
    private String icon;
    private String url;
    private boolean isCollapse;

    public SistemaBoton(){

    }

    public SistemaBoton(int id) {
        this.id = id;
    }

    public SistemaBoton(String url) {
        this.url = url;
    }

    public SistemaBoton(int id, String codigo, String nombre, int idPadre, float value, boolean enabled, String icon, String url, boolean isCollapse) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.idPadre = idPadre;
        this.valor = value;
        this.habilitado = enabled;
        this.icon = icon;
        this.url = url;
        this.isCollapse = isCollapse;
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

    public int getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }

    public float getValue() {
        return valor;
    }

    public void setValue(float value) {
        this.valor = value;
    }

    public boolean getEnabled() {
        return habilitado;
    }

    public void setEnabled(boolean enabled) {
        this.habilitado = enabled;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getCollapse() {
        return isCollapse;
    }

    public void setCollapse(boolean collapse) {
        isCollapse = collapse;
    }
}
