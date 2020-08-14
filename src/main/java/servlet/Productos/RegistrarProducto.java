package servlet.Productos;

import Controladores.GrabarMensaje;
import Controladores.ProductoCategoriaHandler;
import Controladores.ProductoHandler;
import Entities.Persona.PersonaEmpleado;
import Entities.Productos.*;
import Entities.System.SistemaMensaje;
import Entities.System.SistemaModulo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/registrarProducto")
public class RegistrarProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Producto p = new Producto();
        p.setCodigo(req.getParameter("codProd"));
        p.setNombre(req.getParameter("nombreProd"));
        p.setDesc(req.getParameter("descProd"));
        p.setEstado(req.getParameter("estadoProd").equals("1")?true:false);
        p.setProductoProveedor(new ProductoProveedor(Integer.valueOf(req.getParameter("inputProveedor"))));
        p.setProductoFabricante(new ProductoFabricante(Integer.valueOf(req.getParameter("inputFabricante"))));
        p.setProductoCategoria(new ProductoCategoria(Integer.valueOf(req.getParameter("categoriaProd"))));
        ArrayList<ProductoPrecio> precio = new ArrayList<ProductoPrecio>();
        precio.add(new ProductoPrecio(Float.valueOf(req.getParameter("precioProd")),Float.valueOf(req.getParameter("maxDscto"))));
        p.setProductoPrecioActivo(precio);


        p = new ProductoHandler().registrarProducto(p);

        if (p.getId()!=0) {
            //Quiere decir qeu se creo el usuario correctamente
            PersonaEmpleado e = (PersonaEmpleado) req.getSession(true).getAttribute("empleado");
            SistemaMensaje m = new SistemaMensaje();
            m.setEmpleado(e);
            m.setMensaje("Se ha dado de alta al producto: " + p.getCodigo());
            m.setSistemaModulo(new SistemaModulo(6));
            new GrabarMensaje(m);
            req.getRequestDispatcher("productos.jsp").forward(req, resp);
        }


    }
}
