package Controladores;

import Data.DataSesiones;
import Entities.Persona.PersonaEmpleado;
import Entities.System.SistemaBoton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.SQLException;

public class VerificarUsuario {

    public VerificarUsuario(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String url = req.getRequestURI().split("\\/")[2];
        HttpSession session = req.getSession(true);
        PersonaEmpleado e = (PersonaEmpleado) session.getAttribute("empleado");
        if (e==null){
            //response.sendRedirect("login.jsp");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        if (!(new DataSesiones().tieneSesionAbierta(e))){
            session.invalidate();
            resp.sendRedirect("login.jsp");
            //req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
        if(!(new TienePaginaPermitida().tienePaginaPermitida(e,new SistemaBoton(url)))){
            session.setAttribute("paginaNoPermitida", url);
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
        e.setNotificaciones(new BuscarNotificaciones().getAllByUsuario(e));
    }


}
