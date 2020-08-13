package Entities.Productos;

import java.sql.Timestamp;

public class ProductoPrecio {

    private int id;
    private Timestamp fechaDesde;
    private Timestamp fechaHasta;
    private float valor;
    private float maxDscto;
    private String nombre;

    public ProductoPrecio(){

    }

    public ProductoPrecio(int id) {
        this.id = id;
    }

    public ProductoPrecio(int id, Timestamp fechaDesde, Timestamp fechaHasta, float valor, float maxDscto, String nombre) {
        this.id = id;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.valor = valor;
        this.maxDscto = maxDscto;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getMaxDscto() {
        return maxDscto;
    }

    public void setMaxDscto(float maxDscto) {
        this.maxDscto = maxDscto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
