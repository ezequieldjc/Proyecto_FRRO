package Data;

import Controladores.ProductoCategoriaHandler;
import Controladores.ProductoFabricantesHandler;
import Controladores.ProductoPrecioHandler;
import Controladores.ProductoProveedorHandler;
import Entities.Persona.PersonaEmpleado;
import Entities.Productos.*;

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
                " left join producto_precio ppr " +
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

    public Producto registrarProducto (Producto producto) throws SQLException{
        PreparedStatement stmt = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                    "INSERT INTO `proyecto-mysql`.`producto` (`id`, `idProveedor`,`idFabricante`,`idCategoria`,`codigo`,`nombre`,`desc`,`estado`) " +
                            "VALUES (?,?,?,?,?,?,?,?);"
                    , PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setInt(1,producto.getId());
        stmt.setInt(2,producto.getProductoProveedor().getId());
        stmt.setInt(3,producto.getProductoFabricante().getId());
        stmt.setInt(4,producto.getProductoCategoria().getId());
        stmt.setString(5,producto.getCodigo());
        stmt.setString(6,producto.getNombre());
        stmt.setString(7,producto.getDesc());
        stmt.setBoolean(8,producto.getEstado());
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs != null && rs.next()) {
            producto.setId(rs.getInt(1));
        }
        return producto;
    }

    private Producto registrarPrecioInicial (Producto producto) throws SQLException{
        PreparedStatement stmt = null;
        stmt = DataConnectioniMac.getInstancia().getConn().prepareStatement(
                    "insert into `proyecto-mysql`.`producto_precio` (`nombre`,`idProducto`,`valor`,`maxDescuento`) values " +
                            "(?,?,?,?); "
                    , PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, "Precio Inicial");
        stmt.setInt(2,producto.getId());
        stmt.setFloat(3, producto.getProductoPrecioActivo().get(0).getValor());
        stmt.setFloat(4, producto.getProductoPrecioActivo().get(0).getMaxDscto());
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs != null && rs.next()) {
            producto.getProductoPrecioActivo().get(0).setId(rs.getInt(1));
        }
        return producto;
    }


}
