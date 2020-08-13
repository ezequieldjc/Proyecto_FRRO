package Data;

import Controladores.ProductoCategoriaHandler;
import Controladores.ProductoFabricantesHandler;
import Controladores.ProductoPrecioHandler;
import Controladores.ProductoProveedorHandler;
import Entities.Productos.Producto;
import Entities.Productos.ProductoCategoria;
import Entities.Productos.ProductoFabricante;
import Entities.Productos.ProductoProveedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataProducto {

    public ArrayList<Producto> getAll() throws SQLException {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select p.id, p.codigo, p.desc, p.estado, p.id, p.nombre, p.idFabricante, p.idProveedor, idCategoria" +
                " from producto p " +
                " inner join producto_fabricante pf " +
                " on p.idFabricante = pf.id " +
                " inner join producto_proveedor pp " +
                " on p.idProveedor = p.id " +
                " inner join producto_precio ppr " +
                " on p.id = ppr.idProducto;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            Producto p = new Producto();
            p.setCodigo(rs.getString("p.codigo"));
            p.setDesc(rs.getString("p.desc"));
            p.setEstado(rs.getBoolean("p.estado"));
            p.setId(rs.getInt("p.id"));
            p.setNombre(rs.getString("p.nombre"));
            p.setProductoFabricante(new ProductoFabricantesHandler().getOneByID(new ProductoFabricante(rs.getInt("idFabricante"))));
            p.setProductoProveedor(new ProductoProveedorHandler().getOneByID(new ProductoProveedor(rs.getInt("idProveedor"))));
            p.setProductoPrecioActivo(new ProductoPrecioHandler().getActivosByProducto(p));
            p.setProductoCategoria(new ProductoCategoriaHandler().getOneByID(new ProductoCategoria(rs.getInt("idCategoria"))));
            productos.add(p);
        }
        return productos;
    }

    public ArrayList<Producto> getByFilter(Producto producto) throws SQLException {
        //Veo que campos tengo que incluir en el where
        String where = "p.id = p.id";
        if(producto.getProductoCategoria().getId()!=0){
            where += " and p.idCategoria = ";
        }
        if(producto.getCodigo()!=null){
            where += " and p.codigo = ";
        }

        ArrayList<Producto> productos = new ArrayList<Producto>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select p.id, p.codigo, p.desc, p.estado, p.id, p.nombre, p.idFabricante, p.idProveedor, idCategoria" +
                " from producto p " +
                " inner join producto_fabricante pf " +
                " on p.idFabricante = pf.id " +
                " inner join producto_proveedor pp " +
                " on p.idProveedor = p.id " +
                " inner join producto_precio ppr " +
                " on p.id = ppr.idProducto;";
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            //int id, String codigo, String name, String desc, PersonaEmpleado owner, PersonaEmpleado backup
            Producto p = new Producto();
            p.setCodigo(rs.getString("p.codigo"));
            p.setDesc(rs.getString("p.desc"));
            p.setEstado(rs.getBoolean("p.estado"));
            p.setId(rs.getInt("p.id"));
            p.setNombre(rs.getString("p.nombre"));
            p.setProductoFabricante(new ProductoFabricantesHandler().getOneByID(new ProductoFabricante(rs.getInt("idFabricante"))));
            p.setProductoProveedor(new ProductoProveedorHandler().getOneByID(new ProductoProveedor(rs.getInt("idProveedor"))));
            p.setProductoPrecioActivo(new ProductoPrecioHandler().getActivosByProducto(p));
            p.setProductoCategoria(new ProductoCategoriaHandler().getOneByID(new ProductoCategoria(rs.getInt("idCategoria"))));
            productos.add(p);
        }
        return productos;
    }


}
