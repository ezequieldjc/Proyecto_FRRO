package Entities.System;

public class SistemaGlobalConfig {
    private int id;
    private String codigo;
    private String nombreParametro;
    private int numeroParametro;
    private String nombreAtributo;
    private String valorAtributo;
    private SistemaModulo modulo;

    public SistemaGlobalConfig(){

    }

    public SistemaGlobalConfig(int id) {
        this.id = id;
    }

    public SistemaGlobalConfig(String codigo) {
        this.codigo = codigo;
    }

    public SistemaGlobalConfig(int id, String codigo, String nombreParametro, int numeroParametro, String nombreAtributo, String valorAtributo, SistemaModulo modulo) {
        this.id = id;
        this.codigo = codigo;
        this.nombreParametro = nombreParametro;
        this.numeroParametro = numeroParametro;
        this.nombreAtributo = nombreAtributo;
        this.valorAtributo = valorAtributo;
        this.modulo = modulo;
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

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public int getNumeroParametro() {
        return numeroParametro;
    }

    public void setNumeroParametro(int numeroParametro) {
        this.numeroParametro = numeroParametro;
    }

    public String getNombreAtributo() {
        return nombreAtributo;
    }

    public void setNombreAtributo(String nombreAtributo) {
        this.nombreAtributo = nombreAtributo;
    }

    public String getValorAtributo() {
        return valorAtributo;
    }

    public void setValorAtributo(String valorAtributo) {
        this.valorAtributo = valorAtributo;
    }

    public SistemaModulo getModulo() {
        return modulo;
    }

    public void setModulo(SistemaModulo modulo) {
        this.modulo = modulo;
    }
}
