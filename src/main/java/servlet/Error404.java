package servlet;

import Controladores.*;
import Entities.Notificaciones.Notificacion;
import Entities.Notificaciones.NotificacionCategoria;
import Entities.Persona.PersonaEmpleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
        resp.sendRedirect("404.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
