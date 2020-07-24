package servlet;

import Controladores.*;
import Data.DataPersonaEmpleado;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.Persona.PersonaEmpleado;
import Entities.System.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/404error")
public class Error404 extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Notificacion n = new Notificacion();
        n.setCategoria(new NotificacionCategoria(4));
        n.setMensaje("Error 404 - " + req.getRequestURI().split("\\/")[2]);
        PersonaEmpleado e = (PersonaEmpleado) req.getSession(true).getAttribute("empleado");
        if (e!=null){
            n.setResponsable((e));
        } else {
            n.setResponsable(null);
        }
        n.setPrioridad(2);
        //lo comento pues cada vez que cargo menu.jsp se produce un error 404
        new GrabarNotificacion(n);
        resp.sendRedirect("../../error-pages/404error.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
