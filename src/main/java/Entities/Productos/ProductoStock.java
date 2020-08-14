package Entities.Productos;

public class ProductoStock {

    private int id;
    private int idDeposito;
    private float stockActual;
    private float stockMinimo;
    private float stockMaximo;
    private ProductoUnidadMedida productoUnidadMedida;

    public ProductoStock(){

    }
    public ProductoStock(int id) {
        this.id = id;
    }

    public ProductoStock(int id, int idDeposito, float stockActual, float stockMinimo, float stockMaximo, ProductoUnidadMedida productoUnidadMedida) {
        this.id = id;
        this.idDeposito = idDeposito;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.productoUnidadMedida = productoUnidadMedida;
    }

    public ProductoStock(int id, int idDeposito, float stockActual, float stockMinimo, float stockMaximo) {
        this.id = id;
        this.idDeposito = idDeposito;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(int idDeposito) {
        this.idDeposito = idDeposito;
    }

    public float getStockActual() {
        return stockActual;
    }

    public void setStockActual(float stockActual) {
        this.stockActual = stockActual;
    }

    public float getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(float stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public float getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(float stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public ProductoUnidadMedida getProductoUnidadMedida() {
        return productoUnidadMedida;
    }

    public void setProductoUnidadMedida(ProductoUnidadMedida productoUnidadMedida) {
        this.productoUnidadMedida = productoUnidadMedida;
    }

    public boolean estaOK(ProductoStock ps){
        if (this.stockActual>=stockMinimo)
            if (this.stockActual<=stockMaximo)
                return true;
            return false;

    }
}
