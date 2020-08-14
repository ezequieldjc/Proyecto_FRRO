package Entities.Productos;

import java.util.ArrayList;

public class Producto {

    private int id;
    private ProductoProveedor productoProveedor;
    private ProductoFabricante productoFabricante;
    private ArrayList<ProductoPrecio> productoPrecioActivo;
    private ArrayList<ProductoStock> productoStock;
    private ProductoCategoria productoCategoria;
    private String codigo;
    private String nombre;
    private String desc;
    private boolean estado;

    //const
    public Producto(){

    }

    public Producto(int id) {
        this.id = id;
    }

    public Producto(int id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    public Producto(int id, ProductoProveedor productoProveedor, ProductoFabricante productoFabricante,
                    ArrayList<ProductoPrecio> productoPrecioActivo, ArrayList<ProductoStock> productoStock,ProductoCategoria productoCategoria,
                    String codigo, String nombre, String desc, boolean estado) {
        this.id = id;
        this.productoProveedor = productoProveedor;
        this.productoFabricante = productoFabricante;
        this.productoPrecioActivo = productoPrecioActivo;
        this.productoStock = productoStock;
        this.productoCategoria = productoCategoria;
        this.codigo = codigo;
        this.nombre = nombre;
        this.desc = desc;
        this.estado = estado;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductoProveedor getProductoProveedor() {
        return productoProveedor;
    }

    public void setProductoProveedor(ProductoProveedor productoProveedor) {
        this.productoProveedor = productoProveedor;
    }

    public ProductoFabricante getProductoFabricante() {
        return productoFabricante;
    }

    public void setProductoFabricante(ProductoFabricante productoFabricante) {
        this.productoFabricante = productoFabricante;
    }

    public ArrayList<ProductoPrecio> getProductoPrecioActivo() {
        return productoPrecioActivo;
    }

    public void setProductoPrecioActivo(ArrayList<ProductoPrecio> productoPrecioActivo) {
        this.productoPrecioActivo = productoPrecioActivo;
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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ProductoCategoria getProductoCategoria() {
        return productoCategoria;
    }

    public void setProductoCategoria(ProductoCategoria productoCategoria) {
        this.productoCategoria = productoCategoria;
    }

    public ArrayList<ProductoStock> getProductoStock() {
        return productoStock;
    }

    public void setProductoStock(ArrayList<ProductoStock> productoStock) {
        this.productoStock = productoStock;
    }

    public boolean isEstado() {
        return estado;
    }
}
