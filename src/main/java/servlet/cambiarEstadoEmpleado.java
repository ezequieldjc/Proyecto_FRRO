package servlet;

import Controladores.*;
import Data.DataGlobalConfig;
import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;
import Entities.System.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Locale;


@WebServlet("/cambiarEstado")
public class cambiarEstadoEmpleado extends  HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonaEmpleado e = (PersonaEmpleado) req.getSession(true).getAttribute("empleado");
        if (e!=null){
            //Si existe el empleado
            if (new TieneAccionPermitida().tieneAccionPermitidaByCodigoAccion(e,new SistemaAccion("SES"))){
                //Si el usuario tiene permiso para realizar la accion
                if (req.getParameter("usr") != null) {
                    PersonaEmpleado usuario = new BuscarEmpleado().buscarEmpleadoByUsuario(new PersonaEmpleado(req.getParameter("usr")));
                    if (usuario != null ){
                        new CambiarEstadoEmpleado().cambiarEstadoEmpleado(usuario);
                        if (usuario.getEstado()) {
                            //SistemaModulo sistemaModulo, String mensaje, PersonaEmpleado empleado
                            new GrabarMensaje(new SistemaMensaje(new SistemaModulo(1), "Se habilito al usuario" + usuario.getUsuario(), e));
                        } else {
                            new GrabarMensaje(new SistemaMensaje(new SistemaModulo(1), "Se deshabilito al usuario" + usuario.getUsuario(), e));
                        }
                        req.getRequestDispatcher("adminUsuarios.jsp").forward(req, resp);
                    } else {
                        req.getSession(true).setAttribute("errorMensaje","El usuario " + req.getParameter("usr") + " no existe. Contacte al area de sistemas.");
                    }
                }
            } else {
                req.getSession(true).setAttribute("accionNoPermitida","modificar el estado de los usuarios.");
                req.getRequestDispatcher("menu.jsp").forward(req, resp);
            }
        } else
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
    }
}
