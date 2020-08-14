package servlet;

import Controladores.*;
import Data.DataPersonaEmpleado;
import Entities.Persona.PersonaEmpleado;
import Entities.System.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/cerrarSesion")
public class CerrarSesion extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonaEmpleado e = (PersonaEmpleado) req.getSession(true).getAttribute("empleado");
        if (e!=null){
            //Si existe el empleado
            if (new TieneAccionPermitida().tieneAccionPermitidaByCodigoAccion(e,new SistemaAccion("CLS"))){
                //Si el usuario tiene permiso para realizar la accion
                if (req.getParameter("usr") != null) {
                    PersonaEmpleado usuario = new BuscarEmpleado().buscarEmpleadoByUsuario(new PersonaEmpleado(req.getParameter("usr")));
                    if (usuario != null ){
                        new ManejoSesiones().cerrarSesiones(usuario);
                        if (usuario.getEstado()) {
                            //SistemaModulo sistemaModulo, String mensaje, PersonaEmpleado empleado
                            new GrabarMensaje(new SistemaMensaje(new SistemaModulo(1), "Se cerro la sesion del usuario" + usuario.getUsuario(), e));
                        }
                        req.getRequestDispatcher("sesiones.jsp").forward(req, resp);
                    } else {
                        req.getSession(true).setAttribute("errorMensaje","El usuario " + req.getParameter("usr") + " no existe. Contacte al area de sistemas.");
                    }
                }
            } else {
                req.getSession(true).setAttribute("accionNoPermitida","cerrar sesiones de los usuarios.");
                req.getRequestDispatcher("menu.jsp").forward(req, resp);
            }
        } else
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
    }






}
